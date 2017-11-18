SUMMARY = "Small console image for Orange Pi Zero"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
  openssh openssh-keygen \
  kernel-modules \
  xradio \
"

WIFI_SUPPORT = " \
  wpa-supplicant \
"

EXTRA_TOOLS_INSTALL = " \
  init-ifupdown \
  mtd-utils \
"

IMAGE_INSTALL += " \
  ${CORE_OS} \
  ${EXTRA_TOOLS_INSTALL} \
  ${WIFI_SUPPORT} \
"

IMAGE_FEATURES += "package-management"
