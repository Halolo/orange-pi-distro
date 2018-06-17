require linux-opi.inc

COMPATIBLE_MACHINE = "orange-pi-pc|orange-pi-pc-plus"

SRCREV = "b43238f62434cd8c335fe2f04bf7cdcdf56c1e28"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.16 \
  file://defconfig \
  file://Add-Mali-Node.patch \
"
