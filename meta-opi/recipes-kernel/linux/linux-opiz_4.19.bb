require linux-opi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${PN}z:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

COMPATIBLE_MACHINE = "(orange-pi-zero)"

SRCREV = "84df9525b0c27f3ebc2ebb1864fa62a97fdedb7d"

SRC_URI = " \
  git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=master \
  file://defconfig \
  file://0001-Enable_Spi_Nor_Flash.patch \
  file://0002-Add_WiFi_To_Dts.patch \
"
