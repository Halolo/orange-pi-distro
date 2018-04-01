require opipc-image.inc

SUMMARY = "Qt5 image for Orange Pi Pc"
IMAGE_LINGUAS = "en-us"

EXTRA = " \
  htop \
  gdb \
"

QT5_PKGS = " \
  qtbase \
  qtmultimedia \
  qt3d \
  qt5-opengles2-test \
  eglinfo-fb \
  cinematicexperience \ 
"

IMAGE_INSTALL += " \
  ${EXTRA} \
  ${QT5_PKGS} \
"
