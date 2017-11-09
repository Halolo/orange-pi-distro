SUMMARY = "Xradio WiFi driver for orangepi-zero"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

inherit module

PV = "0.1"
PR = "r0"

RDEPENDS_${PN} += "xradio-firmware"

COMPATIBLE_MACHINE = "orange-pi-zero"

SRCREV = "b00ccb0e1524bf53e443fc5777df841b5db3c4e0"

SRC_URI = "git://github.com/fifteenhex/xradio.git;protocol=https \
           file://Add_Targets_To_Makefile.patch \
          "

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "xradio_wlan"
