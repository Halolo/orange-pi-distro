SECTION = "kernel"
DESCRIPTION = "Sunxi Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(orange-pi-pc)"

inherit kernel

require recipes-kernel/linux/linux.inc

# Default is to use stable kernel version
# If you want to use latest git version set to "1"
DEFAULT_PREFERENCE = "-1" 

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
	
# 3.4.113
PV = "3.4.113+git${SRCPV}"
SRCREV = "002a60c148587fcfe3feda2b50c3b80a89963ed8"

SRC_URI = " \
        git://github.com/armbian/linux.git;protocol=git;branch=sun8i \
        file://defconfig \
        file://add-mali-r3p0-fixed.patch \
        file://0001-Fix-compilation-problems-with-more-modern-version-of.patch \
        file://0001-gc2035-camera-improvements.patch \
        file://0011-gpu-drm-Add-Mali-DX910-SW-99002-r2p4-02rel1.patch \
        file://0012-drm-mali-fix-for-3.4.patch \
        file://0013-gpu-drm-mali_drv-fixed-integration-with-3.x-kernels-.patch \
        file://0015-Fix-paths-to-files-in-src-erroneously-searched-for-i.patch \
        file://0017-linux-sunxi-add-missing-compiler-gcc7.h-from-master.patch \
        file://0024-pwm-sunxi-support-for-H3-as-appears-in-loboris-initi.patch \
"

S = "${WORKDIR}/git"
