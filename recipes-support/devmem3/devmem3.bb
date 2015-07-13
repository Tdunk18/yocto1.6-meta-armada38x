SUMMARY = "Simple program to read/write from/to any location in memory"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://devmem3.c;endline=28;md5=5f0654160478fd8b4e6b0d1f7ad8bd1d"
PR = "r0"

SRC_URI = "http://al.robotfuzz.com/~al/wizard/devmem3.c"
SRC_URI[md5sum] = "830a0ed4930fc305834da9b8cce4cef6"
SRC_URI[sha256sum] = "892526ecc4449d5a6fe0f01ff03b22c02fe8d75aa736917cb9deaae54d0620e8"

S = "${WORKDIR}"

CFLAGS += "-DFORCE_STRICT_ALIGNMENT"

do_compile() {
    ${CC} -o devmem3 devmem3.c ${CFLAGS} ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install devmem3 ${D}${bindir}
}
