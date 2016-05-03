require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Armada 38x"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=90fb5e9af37e63370e37e9a9178cc427"
COMPATIBLE_MACHINE_armada38x = "armada38x"

PROVIDES = "u-boot"

PV = "${UBOOT_VERSION}-${UBOOT_MARVELL_VERSION}"
S = "${WORKDIR}/u-boot-${UBOOT_VERSION}/"

# Support for fetching and building U-Boot from Marvell GitHub
require u-boot-armada38x/source-github.inc

# Support for building U-Boot downloaded from Marvell Extranet
#require u-boot-armada38x/source-extranet.inc

SRC_URI += "file://0001-Revert-bin_header_debug-add-XOR-engine-based-memtest.patch \
            file://0002-Revert-bin_header_debug-add-memory-ir-and-training-c.patch \
            file://0003-Revert-bin_header_debug-add-support-for-commands-in-.patch \
            file://0004-Revert-bin_header_debug-add-implementation-of-C-libr.patch \
            file://0005-remove-msoft-float-from-PLATFORM_RELFLAGS.patch \
            file://0006-change-bootcmd-to-use-stage_boot.patch \
"

#file://0005-set-USE_PRIVATE_LIBGCC.patch

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

	if [ "${UBOOT_MARVELL_MACHINE}" == "armada_38x_clearfog" ]
	then
		make ${UBOOT_MARVELL_MACHINE}_config
		make u-boot.mmc
	else
		export CROSS_COMPILE_BH=${TARGET_PREFIX}
		perl build.pl -f spi -b ${UBOOT_MARVELL_MACHINE} -v "yocto" -i spi -c
		cp -f u-boot-a38x-yocto-spi.bin u-boot.bin
	fi
}
