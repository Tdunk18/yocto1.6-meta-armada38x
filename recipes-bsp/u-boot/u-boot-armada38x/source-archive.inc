UBOOT_VERSION = "2013.01"
UBOOT_MARVELL_VERSION = "2015_T1.0"

PV = "${UBOOT_VERSION}-${UBOOT_MARVELL_VERSION}"
S = "${WORKDIR}/u-boot-${UBOOT_VERSION}/"

# U-Boot - Source Files
SRC_URI_armada38x = "ftp://ftp.denx.de/pub/u-boot/u-boot-${UBOOT_VERSION}.tar.bz2 \
                     file://u-boot-${PV}-custom.patch \
                     file://u-boot-2013.01_hard_vfp.patch \
                     file://u-boot-2013.01_default_bootcmd.patch \
"
SRC_URI[md5sum] = "e58a8a7f78972248190d83de0dc362ce"
SRC_URI[sha256sum] = "9ca0f35f4394281312d03c581e24a263cf3e0d9703f15103ff2ead5e5aa43f90"
