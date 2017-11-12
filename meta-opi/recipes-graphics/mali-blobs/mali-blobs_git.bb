SUMMARY = "Mali-400 userspace blobs"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM="file://EULA\sfor\sMali\s400MP\s_AW.pdf;md5=725f991a1cc322aa7a0cd3a2016621c4"

PROVIDES = "virtual/libegl virtual/libgles1 virtual/libgles2 virtual/egl"

SRC_URI = "git://github.com/free-electrons/mali-blobs.git;branch=master"

SRCREV = "cb3e8ece9b2c3a70cbeb3204cd6f30eceaa32023"

S= "${WORKDIR}/git"

PACKAGES = "${PN}"

do_install() {
  install -d ${D}${libdir}
  install -m 0644 ${S}/r6p2/fbdev/lib/lib_fb_dev/lib* ${D}${libdir}/
}

FILES_${PN} = "${libdir}/*"
