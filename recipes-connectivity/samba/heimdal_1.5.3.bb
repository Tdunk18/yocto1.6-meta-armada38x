SUMMARY = "The Heimdal Kerberos 5, PKIX, CMS, GSS-API, SPNEGO, NTLM, Digest-MD5 and, SASL implementation."

DEPENDS = "e2fsprogs ncurses"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2c6f8cfe82d4fdd74355866f0c14d3f"

PV = "1.5.2"

SRC_URI = "http://www.h5l.org/dist/src/heimdal-1.5.3.tar.gz \
"

S = "${WORKDIR}/heimdal-1.5.3"
SRC_URI[md5sum] = "30b379e3de12f332fbd201131f02ffca"
SRC_URI[sha256sum] = "aac27bedb33c341b6aed202af07ccc816146a893148721f8123abbbf93bbfea5"

inherit autotools pkgconfig

EXTRA_OECONF = " \
	--disable-shared \
	--enable-static \
	--without-openldap \
	--without-capng \
	--without-sqlite3 \
	--without-libintl \
	--without-openssl \
	--without-berkeley-db \
	--without-readline \
	--without-libedit \
	--without-hesiod \
	--without-x \
	--disable-heimdal-documentation \
       "

do_configure_append() {
    cp ${B}/*libtool ${B}/lib/libedit/libtool
}

# parallel make fails 8 out of 10 times with -j8 
PARALLEL_MAKE = ""

# Put both compile tools together in $PATH and libexecdir in order to build samba4
do_install_append() {
    ln -sf ${libexecdir}/heimdal/asn1_compile ${D}${bindir}/
    ln -sf ${bindir}/compile_et ${D}${libexecdir}/heimdal/
}

BBCLASSEXTEND = "native"

