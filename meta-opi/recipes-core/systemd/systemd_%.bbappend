FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://opi-wired.network \
"
SRC_URI_orange-pi-zero += " \
    file://opiz-wifi.network \
"

PACKAGECONFIG_append = "networkd resolved"

do_install_append() {
  install -d ${D}${sysconfdir}/systemd/network
  install -m 0644 ${WORKDIR}/opi-wired.network ${D}${sysconfdir}/systemd/network/
}

do_install_append_orange-pi-zero() {
  install -m 0644 ${WORKDIR}/opiz-wifi.network ${D}${sysconfdir}/systemd/network/
}
