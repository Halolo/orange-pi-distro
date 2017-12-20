SUMMARY = "Small console image for Orange Pi Zero"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
  openssh openssh-keygen \
  kernel-modules \
  linux-firmware-rtl8189es \
  wpa-supplicant \
  wireless-tools \
  rtl8189es \
  mali \
  mali-blobs \
"

EXTRA_TOOLS_INSTALL = " \
  mtd-utils \
  xclock \
"

IMAGE_INSTALL += " \
  ${CORE_OS} \
  ${EXTRA_TOOLS_INSTALL} \
  ${XSERVER} \
"

IMAGE_FEATURES += "package-management"
