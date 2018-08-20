SUMMARY = "Realtek out-of-tree kernel driver for rtl8189fs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/autoconf.h;startline=1;endline=18;md5=dbad0abde2e23efe5ea60308662ba921"

inherit module

PV = "git${SRCPV}"

SRC_URI = "git://github.com/jwrdegoede/rtl8189ES_linux.git;branch=rtl8189fs \
	file://0001-Add-module-install.patch \
	"
SRCREV = "cddb07311ec03157643c44154498c9f2268fc431"
S = "${WORKDIR}/git"

EXTRA_OEMAKE += " \
    CONFIG_RTL8189FS=m \
    KSRC=${STAGING_KERNEL_DIR} \
    "
KERNEL_MODULE_AUTOLOAD += "8189fs"
