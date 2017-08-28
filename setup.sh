#!/bin/bash
set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$DIR"

BUILD_DIR="$DIR/build"

usage () {
   echo    "" 1>&2
   echo    "Options:" 1>&2
   echo    "    -b : build the default image (core-image-minimal)" 1>&2
   echo    "" 1>&2
   echo    "    -h = This help menu" 1>&2
   exit 1
}

# Option parsing
while getopts ":hb" opt; do
  case "$opt" in
    b)
      BUILD="yes"
      ;;
    h)
      usage
      ;;
    *)
      usage
      exit 1
      ;;
  esac
done

# Get external layers
git submodule update --init

echo "Removing previous build folder, could take a while..."
rm -rf "$BUILD_DIR"

# Create build environment
. "$DIR/poky/oe-init-build-env" "$BUILD_DIR"

cd "$DIR"

# Add machine and distro
echo '
MACHINE = "orange-pi-zero"
DISTRO = "opiz"' >> "$BUILD_DIR/conf/local.conf"

# Add layers
echo "
BBLAYERS += \" \\
  $DIR/meta-openembedded/meta-oe \\
  $DIR/meta-sunxi \\
  $DIR/meta-opiz \\
  \"" >> "$BUILD_DIR/conf/bblayers.conf"

# Start build
if [ ! -z $BUILD ]; then
  pushd "$BUILD_DIR"
  bitbake opiz-minimal
  popd
fi
