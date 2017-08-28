# orange-pi-zero-distro
Build images using yocto for the [Orange Pi Zero](http://www.orangepi.org/orangepizero/) SBC

This will build a minimal console image

Runs the 4.13-rc6 mainline kernel

[That work](https://github.com/fifteenhex/xradio) on XRADIO X819 WiFi driver for mainline kernel is also in the image as a kernel module and loaded at boot time (firmware binaries for this driver come from [here](https://github.com/armbian/build.git))

The SPI NOR flash memory support is enbled in the kernel and bootloader configuration, so it could be used to run u-boot and boot from USB for example

## Build the image
Clone the external submodules and build the image:

`./setup.sh -b`

## Flash on a sdcard
The rootfs archive, bootloader and kernel images as well as an sdcard image with all needed partitions must have been created in:

`build/tmp-glibc/deploy/images/orange-pi-zero/`

Use dd to flash it on the sdcard (unmount all partitions before):

`dd if=build/tmp-glibc/deploy/images/orange-pi-zero/opiz-minimal-orange-pi-zero.sunxi-sdimg of=/dev/??? bs=1M`

## Boot
The ethernet network interface is configured as a dhcp client. A sshd is running. Use root for login, no password.

The wireless network interface uses wpa-supplicant for its configuration, edit `/etc/wpa-supplicant.conf` to setup the ssid and psk, and run `ifup wlan0` to start the interface.

## Flash u-boot on the SPI NOR flash
If your board has been produced with integrated SPI NOR flash memory, you should see its device file:

`/dev/mtd0`

The distribution provides mtd-utils. Copy the u-boot binary from the image folder on the device:

`scp build/tmp-glibc/deploy/images/orange-pi-zero/u-boot-sunxi-with-spl.bin root@<ip>:`

Then, on the device:

`flash_erase /dev/mtd0 0 128`

`flashcp u-boot-sunxi-with-spl.bin /dev/mtd0`
