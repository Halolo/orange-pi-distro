SUMMARY = "Mali-400 userspace blobs"
LICENSE = "CLOSED"

PROVIDES = "virtual/egl virtual/libegl virtual/libgles1 virtual/libgles2"

INSANE_SKIP_${PN} = "dev-so"

SRC_URI = " \
  git://github.com/free-electrons/mali-blobs.git;branch=master \
  file://egl.pc \
  file://gles_cm.pc \
  file://glesv2.pc \
"

PV = "git${SRCPV}"

SRCREV = "cb3e8ece9b2c3a70cbeb3204cd6f30eceaa32023"

S= "${WORKDIR}/git"

do_install() {
  install -d ${D}${libdir}
  install -m 0644 ${S}/r6p2/fbdev/lib/lib_fb_dev/lib* ${D}${libdir}

  install -d ${D}${libdir}/pkgconfig
  install -m 0644 ${WORKDIR}/*.pc ${D}${libdir}/pkgconfig/

  install -d ${D}${includedir}/EGL
  install -d ${D}${includedir}/GLES
  install -d ${D}${includedir}/GLES2
  install -d ${D}${includedir}/KHR

  install -m 0644 ${S}/r6p2/fbdev/include/EGL/* ${D}${includedir}/EGL/
  install -m 0644 ${S}/r6p2/fbdev/include/GLES/* ${D}${includedir}/GLES/
  install -m 0644 ${S}/r6p2/fbdev/include/GLES2/* ${D}${includedir}/GLES2/
  install -m 0644 ${S}/r6p2/fbdev/include/KHR/* ${D}${includedir}/KHR/
}

FILES_${PN} = "${libdir}/*"
FILES_${PN}-dev = "${includedir}/*"
