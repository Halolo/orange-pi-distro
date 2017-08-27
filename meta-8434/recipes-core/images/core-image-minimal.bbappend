IMAGE_LINGUAS = "en-us"

CORE_OS = " \
  openssh openssh-keygen \
  kernel-modules \
  xradio \
"

WIFI_SUPPORT = " \
  wpa-supplicant \
  iw \
"

EXTRA_TOOLS_INSTALL = " \
  init-ifupdown \
"

IMAGE_INSTALL += " \
  ${CORE_OS} \
  ${EXTRA_TOOLS_INSTALL} \
  ${WIFI_SUPPORT} \
"

IMAGE_FEATURES += "package-management"
