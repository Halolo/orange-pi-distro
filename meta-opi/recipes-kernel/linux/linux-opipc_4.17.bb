require linux-opi.inc

COMPATIBLE_MACHINE = "orange-pi-pc|orange-pi-pc-plus"

SRCREV = "29dcea88779c856c7dc92040a0c01233263101d4"

SRC_URI = " \
  git://github.com/torvalds/linux.git;protocol=git;branch=master \
  file://defconfig \
  file://Add-Mali-Node.patch \
"
