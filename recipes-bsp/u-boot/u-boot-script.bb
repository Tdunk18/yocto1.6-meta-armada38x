LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0adaf0d6160d61a8467973ac69edd367"
DEPENDS = "u-boot-mkimage-native"
# adapted from meta-fsl-arm-extra:u-boot-script-boundary

PV = "r1"

SRC_URI = "file://armada38x-db-bootscript \
           file://armada38x-clearfog-bootscript \
           file://README \
           file://LICENSE \
"

S = "${WORKDIR}/"

inherit deploy

BOOTSCRIPT = "${S}/${MACHINE}-bootscript"
FILES_${PN} += "/boot.scr"

#UPGRADESCRIPT = "${S}/board/boundary/nitrogen6x/6x_upgrade.txt"

do_mkimage () {

    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
                  -n "boot script" -d ${BOOTSCRIPT} boot.scr

#    uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
#                  -n "upgrade script" -d ${UPGRADESCRIPT} \
#                  board/boundary/${MACHINE}/6x_upgrade
}

addtask mkimage after do_compile before do_install

do_deploy () {
    install -d ${DEPLOYDIR}
    install ${S}/boot.scr \
            ${DEPLOYDIR}/boot.scr
#    install ${S}/board/boundary/${MACHINE}/6x_upgrade \
#            ${DEPLOYDIR}/6x_upgrade-${MACHINE}-${PV}-${PR}

#    cd ${DEPLOYDIR}
#    rm -f 6x_bootscript-${MACHINE} 6x_upgrade-${MACHINE}
#    ln -sf 6x_bootscript-${MACHINE}-${PV}-${PR} 6x_bootscript-${MACHINE}
#    ln -sf 6x_upgrade-${MACHINE}-${PV}-${PR} 6x_upgrade-${MACHINE}
}

do_install () {
    install ${S}/boot.scr ${D}/boot.scr
}

addtask deploy after do_install before do_build

do_compile[noexec] = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(armada38x-db|armada38x-clearfog)"
