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
   echo    ""
   echo    "Options:"
   echo    "    -d device"
   echo    "    -m machine (orange-pi-pc or orange-pi-zero)"
   echo    ""
   echo    "Others:"
   echo    "    -h = This help menu"
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
      exit 0
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

if [ "$MACHINE" = "orange-pi-zero" ]; then
  DTB_FILE="$IMG_PATH/uImage-sun8i-h2-plus-orangepi-zero.dtb"
  IMAGE="opiz-minimal"
else
  DTB_FILE="$IMG_PATH/uImage-sun8i-h3-orangepi-pc.dtb"
  IMAGE="opipc-minimal"
fi

RFS_FILE="$IMG_PATH/$IMAGE-$MACHINE.tar.gz"

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
  echo "Cleaning up..."
  cd "$SCRIPT_DIR"
  rm -rf "$BOOT_PART"
  rm -rf "$RFS_PART"
}

exit_script() {
  echo "Unmounting partitions"
  timeout 5m umount "$DEVICE"? &> /dev/null
  cleanup
  if [ "$1" -ne 0 ]; then
    echo "Fail :(" 1>&2
  else
    echo "Success :)"
  fi
}

trap 'exit_script $?' TERM EXIT INT

echo "Creating partition table..."
timeout 1m umount "$DEVICE"? || echo "Continue..."
sed -e 's/\s*\([\+0-9a-zA-Z]*\).*/\1/' << EOF | fdisk -W always -w always "${DEVICE}" > /dev/null
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
  w # write the partition table
EOF
echo -e "Done.\n"

echo "Creating partitions..."
echo "BOOT..."
timeout 5m mkfs.vfat -F 16 -n "BOOT" "${DEVICE}1" > /dev/null
echo "RFS..."
timeout 5m mkfs.ext4 -L "ROOT" "${DEVICE}2" > /dev/null
echo -e "Done.\n"

echo "Mounting partitions..."
rm -rf "$BOOT_PART"
rm -rf "$RFS_PART"
mkdir "$BOOT_PART"
mkdir "$RFS_PART"
timeout 1m mount "${DEVICE}1" "$BOOT_PART"
timeout 1m mount "${DEVICE}2" "$RFS_PART"
echo -e "Done.\n"

echo "Installing boot partition..."
rm -rf "${BOOT_PART:?}/"*
cp "$BOOTSCR_FILE" "$BOOT_PART"
cp "$UIMAGE_FILE" "$BOOT_PART"

if [ "$MACHINE" = "orange-pi-zero" ]; then
  cp "$DTB_FILE" "$BOOT_PART/sun8i-h2-plus-orangepi-zero.dtb"
else
  cp "$DTB_FILE" "$BOOT_PART/sun8i-h3-orangepi-pc.dtb"
fi

echo "Extracting rfs (could take a while)..."
rm -rf "${RFS_PART:?}/"*
timeout 5m tar -xf "$RFS_FILE" -C "$RFS_PART"
timeout 10m sync
  
echo "Writing bootloader..."
timeout 5m dd if="$BOOTLOADER_FILE" of="$DEVICE" bs=1024 seek=8 conv=notrunc

echo -e "Done.\n"
