SUMMARY = "Data Test Program - generic data diagnostic tool"
LICENSE = "BSD-like"
LIC_FILES_CHKSUM = "file://README.1st;endline=27;md5=e21b9743dc8516aff5ec7a3c0d1a7cc4"
PR = "r0"

SRC_URI = "http://www.scsifaq.org/RMiller_Tools/ftp/dt/dt-source.tar.gz"
SRC_URI[md5sum] = "5776233a2d301a50b314306538257a45"
SRC_URI[sha256sum] = "2f27fda643093e07161d128a9cc23cf30c0387f87cd911b904d84217f60a9a2a"

S = "${WORKDIR}/dt.d-WIP"

do_configure() {
    cd ${S}
    ln -sf Makefile.linux Makefile
}

do_compile() {
    oe_runmake -C ${S} CC=${TARGET_PREFIX}gcc LD=${TARGET_PREFIX}ld CFLAGS="${CFLAGS} -D__GNUC__ -DFIFO -DMMAP -D__linux__ -D_GNU_SOURCE" makedep
    oe_runmake -C ${S} CC=${TARGET_PREFIX}gcc LD=${TARGET_PREFIX}ld CFLAGS="${CFLAGS} -DFIFO -DMMAP -DTTY -D__linux__ -D_GNU_SOURCE -D_FILE_OFFSET_BITS=64"
}

do_install() {
    install -D ${S}/dt ${D}/usr/bin/dt
}

do_clean() {
    rm -f ${D}/usr/bin/dt
    oe_runmake -C ${S} clean
}
