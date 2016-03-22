LINUX_VERSION_armada38x = "3.10.70"
MARVELL_LINUX_VERSION = "2015_T1.1"

KBRANCH_armada38x = "linux-3.10.70-15t1"
KBRANCH_clearfog = "linux-3.10.70-15t1-clearfog"
SRCREV_armada38x = "c8ae519736bba6de02ace596b438f14d168648ae"
SRCREV_clearfog = "902739f3353150ac9eb69ad995098f3079d862a3"
SRC_URI_armada38x = "git://git@github.com/MarvellEmbeddedProcessors/linux-marvell.git;branch=${KBRANCH};protocol=https;nocheckout=1"

#PV = "${LINUX_VERSION}+git${SRCPV}"       # for versioning by git commit
