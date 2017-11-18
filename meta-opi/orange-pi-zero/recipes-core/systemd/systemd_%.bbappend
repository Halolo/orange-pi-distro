FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
  file://opiz-wifi.network \
"

do_install_append() {
  install -m 0644 ${WORKDIR}/opiz-wifi.network ${D}${sysconfdir}/systemd/network/
}
