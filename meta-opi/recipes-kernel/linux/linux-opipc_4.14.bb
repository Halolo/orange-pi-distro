require linux-opi.inc

COMPATIBLE_MACHINE = "orange-pi-pc|orange-pi-pc-plus"

SRCREV = "cb31337027aaf606dc77cd423dc54197bb5ed16c"

SRC_URI = " \
  git://github.com/megous/linux.git;protocol=git;branch=orange-pi-4.14 \
  file://defconfig \
  file://Add-Mali-Node.patch \
  file://Enable-Dvfs.patch \
"
