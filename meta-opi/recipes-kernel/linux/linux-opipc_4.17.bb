require linux-opi.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${PN}pc-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

COMPATIBLE_MACHINE = "^orange-pi-pc$"

SRCREV = "a93307e4a443fb50b85155567b467216ee59fd11"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.17 \
  file://defconfig \
  file://Add-Custom-Ioctl-In-Sun4i-Drm.patch \
"
