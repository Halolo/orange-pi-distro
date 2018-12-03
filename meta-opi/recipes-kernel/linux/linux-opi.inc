SECTION = "kernel"
DESCRIPTION = "Linux kernel for Allwiner H3 orangepi"
LICENSE = "GPLv2"

PN = "linux-opi"

inherit kernel

require recipes-kernel/linux/linux.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
