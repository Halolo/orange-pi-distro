DESCRIPTION = "OpenGL ES quake3"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=87113aa2b484c59a17085b5c3f900ebf"

PV = "git${SRCREV}"

SRCREV = "3e1599ac4bed2adb125600fd99b44f4167a75cd9"

DEPENDS = "libsdl2 virtual/libgles2"

SRC_URI = "git://github.com/ioquake/ioq3.git;branch=master;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "COMPILE_PLATFORM=linux COMPILE_ARCH=${TARGET_ARCH} USE_OPENAL=0"

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/build/release-linux-${TARGET_ARCH}/*.${TARGET_ARCH} ${D}/${bindir}

	install -d ${D}/${libdir}
	install -m 0755 ${S}/build/release-linux-${TARGET_ARCH}/*.so ${D}/${libdir}

	install -d ${D}/${datadir}/games/quake3/baseq3
	install -m 0644 ${S}/build/release-linux-${TARGET_ARCH}/baseq3/*.so ${D}/${datadir}/games/quake3/baseq3/
}

FILES_${PN}-dbg += "${datadir}/games/quake3/baseq3/.debug"
FILES_${PN} += " \
  ${datadir}/games/quake3/baseq3/ \
  ${bindir} \
  ${libdir} \
"
