require linux-opi.inc

COMPATIBLE_MACHINE = "orange-pi-pc|orange-pi-pc-plus"

SRCREV = "515930d8ac6075800531d2fc88084fd8ecbb6592"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.16 \
  file://defconfig \
  file://Add-Mali-Node.patch \
"
