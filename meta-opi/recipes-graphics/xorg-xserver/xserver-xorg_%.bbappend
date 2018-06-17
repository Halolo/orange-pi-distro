FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG_remove = "glamor"

SRC_URI += "file://xorg.conf"

do_install_append() {
  install -d ${D}${sysconfdir}/X11
  install -m 0644 ${WORKDIR}/xorg.conf ${D}${sysconfdir}/X11/
}
