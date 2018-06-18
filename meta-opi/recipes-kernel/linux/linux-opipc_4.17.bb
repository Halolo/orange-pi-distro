require linux-opi.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

COMPATIBLE_MACHINE = "orange-pi-pc|orange-pi-pc-plus"

SRCREV = "29dcea88779c856c7dc92040a0c01233263101d4"

SRC_URI = " \
  git://github.com/torvalds/linux.git;protocol=git;branch=master \
  file://defconfig \
  file://Add-Mali-Node.patch \
  file://Add-Custom-Ioctl-In-Sun4i-Drm.patch \
"
