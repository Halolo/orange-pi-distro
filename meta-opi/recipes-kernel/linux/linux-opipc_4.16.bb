require linux-opi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${PN}pc-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "^orange-pi-pc$"

SRCREV = "b43238f62434cd8c335fe2f04bf7cdcdf56c1e28"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.16 \
  file://defconfig \
  file://Add-Custom-Ioctl-In-Sun4i-Drm.patch \
  file://add-mali-node-to-h3-dtsi.patch \
"
