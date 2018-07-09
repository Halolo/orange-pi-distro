SUMMARY = "Mali-400 driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/linux/license/gpl/mali_kernel_license.h;endline=9;md5=2f2b0dc0c9b1b6b6dcc45fde0fe5f99a"

inherit module

COMPATIBLE_MACHINE = "orange-pi-pc"

PV = "r8p1+git${SRCPV}"

SRCREV = "8e6b7d25b13089d53dbfc1ebd9e8737b0dc809cb"

SRC_URI = "git://github.com/mripard/sunxi-mali.git;protocol=git"

S = "${WORKDIR}/git/r8p1/src/devicedrv/mali"

do_patch() {
  cd ${WORKDIR}/git/r8p1/

  quilt push -a
}

MODULES_INSTALL_TARGET = "install"

EXTRA_OEMAKE_orange-pi-pc-plus = "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=0 USING_DEVFREQ=0 KDIR=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OEMAKE_orange-pi-pc = "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=1 USING_DEVFREQ=1 KDIR=${STAGING_KERNEL_BUILDDIR}"
