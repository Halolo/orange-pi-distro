require recipes-graphics/mesa/mesa.inc
LIC_FILES_CHKSUM="file://docs/license.html;md5=725f991a1cc322aa7a0cd3a2016621c4"

SRC_URI = "git://github.com/yuq/mesa-lima.git;branch=lima-17.2"

SRCREV = "889159ee511b19c45749eef3943468b02649088b"

S= "${WORKDIR}/git"

DEPENDS += "python-mako-native zlib"

inherit pythonnative

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
