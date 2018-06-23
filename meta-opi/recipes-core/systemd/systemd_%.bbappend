FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://opi-wired.network \
"

do_install_append() {
  install -d ${D}${sysconfdir}/systemd/network
  install -m 0644 ${WORKDIR}/opi-wired.network ${D}${sysconfdir}/systemd/network/
}
