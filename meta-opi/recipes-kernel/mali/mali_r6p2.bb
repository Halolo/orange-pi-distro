SUMMARY = "Mali-400 driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/linux/license/gpl/mali_kernel_license.h;md5=1436c0d104589824163a3eb50fbb5050"

inherit module

COMPATIBLE_MACHINE = "orange-pi-pc"

PV = "r6p2+git${SRCPV}"

SRCREV = "22858d78ee5d647c773330ed01187ca082818665"

SRC_URI = "git://github.com/mripard/sunxi-mali.git;protocol=git"

S = "${WORKDIR}/git/r6p2/src/devicedrv/mali"

do_patch() {
  cd ${WORKDIR}/git/r6p2/

  quilt push -a
}

MODULES_INSTALL_TARGET = "install"

EXTRA_OEMAKE = "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=0 USING_DEVFREQ=0 KDIR=${STAGING_KERNEL_BUILDDIR}"
