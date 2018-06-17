SUMMARY = "Open-source X.org graphics driver for ARM graphics"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=10ce5de3b111315ea652a5f74ec0c602"

require recipes-graphics/xorg-driver/xorg-driver-video.inc

PV = "1.4.1+git${SRCPV}"

COMPATIBLE_MACHINE = "orange-pi-pc"

SRCREV = "736800867007cfa3a7e260bfae70b72949d6f48e"

SRC_URI = "git://github.com/mripard/xf86-video-armsoc.git;protocol=git;branch=sunxi-mali"

inherit autotools

S = "${WORKDIR}/git"

PACKAGE_ARCH_$MACHINE = "${SOC_FAMILY_PKGARCH}"
