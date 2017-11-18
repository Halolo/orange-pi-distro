SUMMARY = "Mali-400 driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/linux/license/gpl/mali_kernel_license.h;md5=1436c0d104589824163a3eb50fbb5050"

inherit module

PV = "0.1"
PR = "r0"

COMPATIBLE_MACHINE = "orange-pi-pc"

SRCREV = "5b7b3c0b6e1736676c90928816d271debbb09d95"

SRC_URI = "git://github.com/mripard/sunxi-mali.git;protocol=git"

S = "${WORKDIR}/git/r6p2/src/devicedrv/mali"

do_patch() {
  cd ${WORKDIR}/git/r6p2

  for patch in ${WORKDIR}/git/patches/*.patch;
  do
    patch -p1 < $patch
  done
}

MODULES_INSTALL_TARGET = "install"

EXTRA_OEMAKE = "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=1 USING_DEVFREQ=1 KDIR=${STAGING_KERNEL_BUILDDIR}"

KERNEL_MODULE_AUTOLOAD += "mali"
