SUMMARY = "Mali-400 driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/linux/license/gpl/mali_kernel_license.h;md5=1436c0d104589824163a3eb50fbb5050"

inherit module

COMPATIBLE_MACHINE = "orange-pi-pc"

PV = "r6p2+git${SRCPV}"

SRCREV = "d13f9cd1af126c07fc77f32838b9a8806ff8c83c"

SRC_URI = "git://github.com/mripard/sunxi-mali.git;protocol=git"

S = "${WORKDIR}/git/r6p2/src/devicedrv/mali"

do_patch() {
  cd ${WORKDIR}/git/r6p2/

  quilt push -a
}

MODULES_INSTALL_TARGET = "install"

EXTRA_OEMAKE = "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=0 USING_DEVFREQ=0 KDIR=${STAGING_KERNEL_BUILDDIR}"
