SUMMARY = "Hierarchical, reference counted memory pool system with destructors"
HOMEPAGE = "http://talloc.samba.org"
SECTION = "libs"

LICENSE = "LGPL-3.0+ & GPL-3.0+"
LIC_FILES_CHKSUM = "file://talloc.c;beginline=11;endline=26;md5=a10f1de43976e2ab16decd697c134776"

SRC_URI = "http://samba.org/ftp/${BPN}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "3e285de2228ae67ff0a0f5cec658f627"
SRC_URI[sha256sum] = "7aa5f75b22d4ef9c737b25515f2a2837ddc13014ff4ac6e58dd9e311f41f2cb0"

inherit waf-samba

EXTRA_OECONF += "--disable-rpath \
                 --disable-rpath-install \
                 --bundled-libraries=NONE \
                 --builtin-libraries=replace \
                 --disable-silent-rules \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix} \
                "

PACKAGES += "libtalloc libtalloc-dbg libtalloc-dev pytalloc pytalloc-dbg pytalloc-dev"

#ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = ""
FILES_${PN}-dev = ""
FILES_${PN}-dbg = ""

FILES_libtalloc = "${libdir}/libtalloc${SOLIBS}"
FILES_libtalloc-dbg = "/usr/src/debug/ \
                       ${libdir}/.debug/libtalloc*"
FILES_libtalloc-dev = "${includedir}/ \
                       ${libdir}/libtalloc${SOLIBSDEV} \
                       ${libdir}/pkgconfig/"

FILES_pytalloc = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/* \
                  ${libdir}/libpytalloc-util${SOLIBS} \
                 "
FILES_pytalloc-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug \
                      ${libdir}/.debug/libpytalloc*"
FILES_pytalloc-dev = "${libdir}/libpytalloc-util${SOLIBSDEV}"
RDEPENDS_pytalloc = "python"
