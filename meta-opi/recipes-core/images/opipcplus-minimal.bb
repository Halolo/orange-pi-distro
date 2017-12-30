SUMMARY = "Small console image for Orange Pi PC PLUS"
IMAGE_LINGUAS = "en-us"

require opipc-image.inc

CORE_OS += " \
  linux-firmware-rtl8189es \
  wpa-supplicant \
  wireless-tools \
  rtl8189es \
"
