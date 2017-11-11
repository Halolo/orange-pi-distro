SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(orange-pi-zero|orange-pi-pc)"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/linux.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

# 4.13.0-rc6
PV = "4.13.0+git${SRCPV}"
SRCREV = "383584ac020b8c34f88f31aa66062628c80fd46d"

SRC_URI = " \
        git://github.com/torvalds/linux.git;protocol=git;branch=master \
        file://defconfig \
"

SRC_URI_orange-pi-zero += " \
        file://Add_WiFi_To_Dts.patch \
        file://Enable_Spi_Nor_Flash.patch \
"

S = "${WORKDIR}/git"
