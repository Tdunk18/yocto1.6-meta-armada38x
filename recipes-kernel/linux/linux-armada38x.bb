DESCRIPTION = "Marvell Kernel for Armada 38x"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

SRC_URI = "git://git@github.com/EmbeddedProcessorsSW/linux-armada38x.git;branch=${SRCBRANCH};protocol=ssh \
           file://defconfig"

LOCALVERSION = "+yocto"
SRCBRANCH = "3.10.39-armada38x-2014.T3"
SRCREV = "e9d3d04a07fff114fa2904881160a17d8fb610e1"
DEPENDS += "lzop-native bc-native"
COMPATIBLE_MACHINE = "(armada38x.*)"

S = "${WORKDIR}/git"
