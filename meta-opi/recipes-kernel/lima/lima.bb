SUMMARY = "OpenSource dirver for MALI-400"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "0.1"
PR = "r0"

COMPATIBLE_MACHINE = "orange-pi-pc"

SRCREV = "7e8cecd6997412312261aaf50bd83cd63a240d7f"

SRC_URI = " \
  git://github.com/yuq/linux-lima.git;protocol=https;branch=lima-4.13 \
  file://Add-Target-To-Makefile.patch \
"

S = "${WORKDIR}/git/drivers/gpu/drm/lima"

FILES_${PN} = "/lib/*"
