require linux-opi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${PN}pcplus:"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "(orange-pi-pc-plus)"

SRCREV = "de9fae3ac976617431cbd83c069da2a986f546fb"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.14 \
  file://defconfig \
  file://0002-arm-dts-sun8i-h3-orangepi-2-Enable-UART3.patch \
"
