require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Solidrun Armada 38x ClearFog board"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=90fb5e9af37e63370e37e9a9178cc427"
COMPATIBLE_MACHINE = "armada38x-clearfog"

PROVIDES = "u-boot"

PV = "v2013.01-2014_T3.0"

SRCBRANCH = "2013.01-armada38x-2014.T3"
SRCREV = "37846d407561f96039d1faec30c2b252607f5418"
SRC_URI = "git://git@github.com/EmbeddedProcessorsSW/u-boot-armada38x;branch=${SRCBRANCH};protocol=ssh \
           file://u-boot-2013.01-2014_T3.0_hard_vfp.patch \
"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]
	then
		echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
		echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
	fi

	export CROSS_COMPILE=${TARGET_PREFIX}
	export CROSS_COMPILE_BH=${TARGET_PREFIX}
	perl build.pl -f spi -b ${UBOOT_MARVELL_MACHINE} -v "yocto" -i spi -c
	cp -f u-boot-a38x-yocto-spi.bin u-boot.bin
}
