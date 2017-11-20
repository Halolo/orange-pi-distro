FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://opi-wired.network \
"

PACKAGECONFIG_append = "networkd resolved"

do_install_append() {
  install -d ${D}${sysconfdir}/systemd/network
  install -m 0644 ${WORKDIR}/opi-wired.network ${D}${sysconfdir}/systemd/network/

  ln -s ../run/systemd/resolve/resolv.conf ${D}${sysconfdir}/resolv.conf
}
