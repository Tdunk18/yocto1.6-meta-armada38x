# linux-yocto-custom.bb:
#
#   A yocto-bsp-generated kernel recipe that uses the linux-yocto and
#   oe-core kernel classes to apply a subset of yocto kernel
#   management to git managed kernel repositories.
#
# Warning:
#
#   Building this kernel without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition:
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition:
#            SRC_URI += "file://feature.scc"
#

require recipes-kernel/linux/linux-yocto.inc

# Support for fetching and building kernel from Marvell GitHub
require linux-yocto-custom/source-github.inc

# Support for building kernel downloaded from Marvell Extranet
#require linux-yocto-custom/source-extranet.inc

PV ?= "${LINUX_VERSION}-${MARVELL_LINUX_VERSION}"
PR = "r0"
LINUX_VERSION_EXTENSION_armada38x = "-marvell"
COMPATIBLE_MACHINE_armada38x = "${MACHINE}"

# kernel cannot be built out of tree
B = "${S}"

SRC_URI_append_armada38x += "file://defconfig \
                             file://enable-ftrace.cfg \
"

SRC_URI_append_armada38x-be += "file://big-endian.cfg"
SRC_URI_append_clearfog += "file://add-clearfog-dts.patch"
