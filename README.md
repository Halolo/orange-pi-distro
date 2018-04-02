# orange-pi-distro
Build images using yocto for:
- [Orange Pi Zero](http://www.orangepi.org/orangepizero/) SBC
- [Orange Pi Pc](http://www.orangepi.org/orangepipc/) SBC

## Orange Pi Zero
This will build a minimal console image

Runs the 4.13-rc6 mainline kernel

[That work](https://github.com/fifteenhex/xradio) on XRADIO X819 WiFi driver for mainline kernel is also in the image as a kernel module and loaded at boot time (firmware binaries for this driver come from [here](https://github.com/armbian/build.git))

The SPI NOR flash memory support is enbled in the kernel and bootloader configuration, so it could be used to run u-boot and boot from USB for example

## Orange Pi Pc [Plus]

Runs a [4.16 kernel](https://github.com/megous/linux), with HDMI support and [Mali driver](https://github.com/mripard/sunxi-mali)

It's the r6p2 fb driver and the matching [r6p2 fb user space lib](https://github.com/free-electrons/mali-blobs), to be used with QT using the EGLFS platform (eglfs_mali)

:warning: WIP

The driver is loaded correctly but eglinfo-fb, the qt egl test app and the [sunxi-mali test app](https://github.com/linux-sunxi/sunxi-mali/tree/master/test) fails the same way: segfault in libMali.so

```
mali: loading out-of-tree module taints kernel.
Allwinner sunXi mali glue initialized
Mali: 
Found Mali GPU Mali-400 MP r1p1
Mali: 
2+0 PP cores initialized
Mali: 
Mali device driver loaded
```

```
Thread 1 "test-mali" received signal SIGSEGV, Segmentation fault.
0xb69ca090 in _mali_osu_lock_wait () from /usr/lib/libMali.so
(gdb) bt
#0  0xb69ca090 in _mali_osu_lock_wait () from /usr/lib/libMali.so
#1  0xb69c2fd2 in _mali_base_common_mem_add_phys_mem () from /usr/lib/libMali.so
#2  0xb69bd8f4 in __egl_platform_display_map_framebuffer_memory () from /usr/lib/libMali.so
#3  0xb69bda9c in __egl_platform_init_display_fbdev () from /usr/lib/libMali.so
#4  0xb6f24098 in _egl_initialize () from /usr/lib/libEGL.so
#5  0xb6f24962 in eglInitialize () from /usr/lib/libEGL.so
#6  0x00010ce4 in main (argc=<optimized out>, argv=<optimized out>) at /usr/src/debug/test-mali/git-r0/git/test/test.c:156
```

## Build the images
Clone the external submodules :

`git submodule update --init`

Source the file `source-me`:

`. source-me <machine>`

With <machine> = "orange-pi-zero" or "orange-pi-pc"

And build the image:

`bitbake <image>`

With <image> = "opiz-minimal" or "opipc-minimal"

## Flash on a sdcard
The rootfs archive, bootloader and kernel images as well as an sdcard image with all needed partitions must have been created in:

`build/tmp-glibc/deploy/images/<machine>/`

Use dd to flash it on the sdcard (unmount all partitions before):

`dd if=build/tmp-glibc/deploy/images/<machine>/<image>-<machine>.sunxi-sdimg of=/dev/??? bs=1M`

To create a sdcard with root partition extended to all the device, use this script:

`sudo ./flash-sdcard.sh -d /dev/???`

## Boot
The ethernet network interface is configured as a dhcp client. A sshd is running. Use root for login, no password.

For Orange Pi Zero, The wireless network interface uses wpa_supplicant for its configuration, edit `/etc/wpa_supplicant/wpa_supplicant-wlan0.conf` to setup the ssid and psk. Or run `wpa_passphrase <SSID> <PASSPHRASE> >> /etc/wpa_supplicant/wpa_supplicant-wlan0.conf`

## Flash u-boot on the SPI NOR flash (Orange Pi Zero)
If your board has been produced with integrated SPI NOR flash memory, you should see its device file:

`/dev/mtd0`

The distribution provides mtd-utils. Copy the u-boot binary from the image folder on the device:

`scp build/tmp-glibc/deploy/images/orange-pi-zero/u-boot-sunxi-with-spl.bin root@<ip>:`

Then, on the device:

`flash_erase /dev/mtd0 0 128`

`flashcp u-boot-sunxi-with-spl.bin /dev/mtd0`
