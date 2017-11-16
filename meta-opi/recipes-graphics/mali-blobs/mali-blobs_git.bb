SUMMARY = "Mali-400 userspace blobs"
LICENSE = "CLOSED"

PROVIDES = "virtual/libegl virtual/libgles1 virtual/libgles2 virtual/egl"

SRC_URI = "git://github.com/free-electrons/mali-blobs.git;branch=master"

SRCREV = "cb3e8ece9b2c3a70cbeb3204cd6f30eceaa32023"

S= "${WORKDIR}/git"

do_install() {
  install -d ${D}${libdir}
  install -m 0644 ${S}/r6p2/fbdev/lib/lib_fb_dev/lib* ${D}${libdir}/
}

FILES_${PN} = "${libdir}/*"
FILES_${PN}-dev = ""
