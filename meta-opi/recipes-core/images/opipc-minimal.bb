SUMMARY = "Small console image for Orange Pi Zero"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
  openssh openssh-keygen \
  kernel-modules \
  sunxi-mali \
"

EXTRA_TOOLS_INSTALL = " \
  mtd-utils \
"

IMAGE_INSTALL += " \
  ${CORE_OS} \
  ${EXTRA_TOOLS_INSTALL} \
"

IMAGE_FEATURES += "package-management"
