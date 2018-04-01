require linux-opi.inc

COMPATIBLE_MACHINE = "(orange-pi-zero)"

SRCREV = "383584ac020b8c34f88f31aa66062628c80fd46d"

SRC_URI = " \
  git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;branch=master \
  file://defconfig \
  file://Add_WiFi_To_Dts.patch \
  file://Enable_Spi_Nor_Flash.patch \
"
