# orange-pi-zero-distro
Build images using yocto for the [Orange Pi Zero](http://www.orangepi.org/orangepizero/) SBC

This will build the yocto minimal console image extended with *iw* and *wpa_supplicant*

Runs the 4.13-rc6 mainline kernel

[That work](https://github.com/fifteenhex/xradio) on XRADIO X819 WiFi driver for mainline kernel is also in the image as a kernel module and loaded at boot time (firmware binaries for this driver come from [here](https://github.com/armbian/build.git))

## Build the image
Clone the external submodules and build the image:
`./setup.sh -b`

## Flash on a sdcard
The rootfs archive, bootloader and kernel images as well as an sdcard image with all needed partitions must have been created in 
`build/tmp-glibc/deploy/images/orange-pi-zero/`
Use dd to flash it on the sdcard (unmount all partitions before):
`dd if=build/tmp-glibc/deploy/images/orange-pi-zero/core-image-minimal-orange-pi-zero.sunxi-sdimg of=/dev/??? bs=1M`

## Boot
The ethernet network interface is configured as a dhcp client. A sshd is running. Use root for login, no password.

The wireless network interface uses wpa-supplicant for its configuration, edit `/etc/wpa-supplicant.conf` to setup the ssid and psk, and run `ifup wlan0` to start the interface.
