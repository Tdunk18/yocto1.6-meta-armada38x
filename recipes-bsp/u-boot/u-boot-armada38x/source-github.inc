UBOOT_VERSION = "2013.01"
UBOOT_MARVELL_VERSION = "2015_T1.0"

SRCBRANCH_armada38x = "u-boot-2013.01-15t1"
SRCBRANCH_clearfog = "u-boot-2013.01-15t1-clearfog"
SRCREV_armada38x = "b21a7137318cdccd1d6569c27dddd33447328770"
SRCREV_clearfog = "c1d6f3e8e315c3843147c74013ed915231774a58"
SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/u-boot-armada38x;branch=${SRCBRANCH};protocol=https \
           file://u-boot-2013.01_hard_vfp.patch \
           file://u-boot-2013.01_default_bootcmd.patch \
"

#PV = "${LINUX_VERSION}+git${SRCPV}"       # for versioning by git commit
S = "${WORKDIR}/git"
