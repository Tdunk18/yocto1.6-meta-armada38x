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

PR = "r1"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_armada38x = "3.10.70"
LINUX_VERSION_EXTENSION_armada38x = "-marvell"
COMPATIBLE_MACHINE_armada38x = "${MACHINE}"

# kernel cannot be built out of tree
B = "${S}"

KBRANCH_armada38x = "3.10"
SRCREV_armada38x = "42abc0893829cedb8cf9c9855f3b3e93f6a9c4c0"

SRC_URI_armada38x = "git://git@github.com/EmbeddedProcessorsSW/linux-armada38x.git;branch=${KBRANCH};protocol=ssh;nocheckout=1 \
					 file://defconfig"
					 

