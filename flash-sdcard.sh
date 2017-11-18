#!/bin/bash
# Quit on error
set -e

# Make sure only root can run our script
if [ "$(id -u)" != "0" ]; then
   echo "This script must be run as root" 1>&2
   exit 1
fi

# run in script dir
SCRIPT_DIR="$(dirname "$0")"
cd "$SCRIPT_DIR"

# Usage if bad arguments were passed
usage () {
   echo    "" 1>&2
   echo    "Options:" 1>&2
   echo    "    -d device" 1>&2
   echo    "    -m machine (orange-pi-pc or orange-pi-zero)" 1>&2
   echo    "" 1>&2
   echo    "Others:" 1>&2
   echo    "    -h = This help menu" 1>&2
   exit 1
}

# Option parsing
while getopts ":hd:m:" opt; do
  case "$opt" in
    d)
      DEVICE="$OPTARG"
      ;;
    m)
      MACHINE="$OPTARG"
      ;;
    h)
      usage
      ;;
    *)
      usage
      exit 1
      ;;
  esac
done

IMG_PATH="$SCRIPT_DIR/build/tmp-glibc/deploy/images/$MACHINE"

BOOTLOADER_FILE="$IMG_PATH/u-boot-sunxi-with-spl.bin"
BOOTSCR_FILE="$IMG_PATH/boot.scr"
UIMAGE_FILE="$IMG_PATH/uImage"
RFS_FILE="$IMG_PATH/opiz-minimal-$MACHINE.tar.gz"

if [ "$MACHINE" = "orange-pi-zero" ]; then
  DTB_FILE="$IMG_PATH/uImage-sun8i-h2-plus-orangepi-zero.dtb"
else
  DTB_FILE="$IMG_PATH/uImage-sun8i-h3-orangepi-pc.dtb"
fi

BOOT_PART="$SCRIPT_DIR/p1"
RFS_PART="$SCRIPT_DIR/p2"

if [ -z "$DEVICE" ]; then
  echo "No devices" 1>&2
  usage
  exit 1
fi

if [ ! -e "$BOOTSCR_FILE" ] || [ ! -e "$UIMAGE_FILE" ] || [ ! -e "$DTB_FILE" ] || [ ! -e "$RFS_FILE" ] || [ ! -e "$BOOTLOADER_FILE" ]; then
  echo "Invalid image path" 1>&2
  usage
  exit 1
fi

cleanup() {
  echo "Cleaning up..." 1>&2
  cd "$SCRIPT_DIR"
  rm -rf "$BOOT_PART"
  rm -rf "$RFS_PART"
}

exit_script() {
  cleanup
  if [ "$1" -ne 0 ]; then
    echo "Unmounting partitions" 1>&2
    cd "$SCRIPT_DIR"
    timeout 5m umount "$DEVICE"?* &> /dev/null
    echo "Fail :(" 1>&2
  else
    echo "Success :)" 1>&2
  fi
}

trap 'exit_script $?' TERM EXIT INT

echo "Creating partition table..." 1>&2
timeout 1m umount "$DEVICE"?* || echo "Continue..." 1>&2
sleep 5
sed -e 's/\s*\([\+0-9a-zA-Z]*\).*/\1/' << EOF | fdisk -w always "${DEVICE}" > /dev/null
  o # New partition table
  n # new partition
    # default
    # default
  2048 # 2048
  +20M # 20M for boot
  t # partition type
  c # win 95 fat32
  a # bootable
  n # new partition
    # default
    # default
    # default
    # default
  p # print the in-memory partition table
  w # write the partition table
  q # and we're done
EOF
sleep 5
timeout 1m umount "$DEVICE"?* || echo "Continue..." 1>&2
echo -e "Done.\n" 1>&2

echo "Creating partitions..." 1>&2
echo "BOOT..." 1>&2
timeout 5m mkfs.vfat -F 16 -n "BOOT" "${DEVICE}1" > /dev/null
echo "RFS..." 1>&2
timeout 5m mkfs.ext4 -L "ROOT" "${DEVICE}2" > /dev/null
echo -e "Done.\n" 1>&2

echo "Mounting partitions..." 1>&2
rm -rf "$BOOT_PART"
rm -rf "$RFS_PART"
mkdir "$BOOT_PART"
mkdir "$RFS_PART"
timeout 1m mount "${DEVICE}1" "$BOOT_PART"
timeout 1m mount "${DEVICE}2" "$RFS_PART"
echo -e "Done.\n" 1>&2

echo "Installing boot partition..." 1>&2
rm -rf "${BOOT_PART:?}/"*
cp "$BOOTSCR_FILE" "$BOOT_PART"
cp "$UIMAGE_FILE" "$BOOT_PART"

if [ "$MACHINE" = "orange-pi-zero" ]; then
  cp "$DTB_FILE" "$BOOT_PART/sun8i-h2-plus-orangepi-zero.dtb"
else
  cp "$DTB_FILE" "$BOOT_PART/sun8i-h3-orangepi-pc.dtb"
fi

echo "Extracting rfs (could take a while)..." 1>&2
rm -rf "${RFS_PART:?}/"*
timeout 5m tar -xf "$RFS_FILE" -C "$RFS_PART"
timeout 10m sync
  
echo "Unmounting partitions" 1>&2
timeout 5m umount "$DEVICE"?* &> /dev/null

echo "Writing bootloader..."
timeout 5m dd if="$BOOTLOADER_FILE" of="$DEVICE" bs=1024 seek=8 conv=notrunc

echo -e "Done.\n" 1>&2
