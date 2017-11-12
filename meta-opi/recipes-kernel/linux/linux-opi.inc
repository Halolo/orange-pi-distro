SECTION = "kernel"
DESCRIPTION = "Linux-lima kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PN = "linux-opi"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/linux.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

# 4.14
PV = "4.14+git${SRCPV}"
SRCREV = "a215a0612821c9db0cc688efae22274735346e3d"

SRC_URI = " \
        git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.14 \
        file://defconfig \
"

S = "${WORKDIR}/git"
