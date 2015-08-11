DESCRIPTION = "Openswan is an Open Source implementation of IPsec for the \
Linux operating system."
HOMEPAGE = "http://www.openswan.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65b1c7b9e2985a7204bd3aba4ddc7132"
DEPENDS = "gmp flex-native openssl"
RRECOMMENDS_openswan = "kernel-module-ipsec"
RDEPENDS_nylon = "perl"
PR = "r0"

SRC_URI = "http://download.openswan.org/openswan/old/openswan-2.6/openswan-2.6.38.tar.gz \
	   file://openswan-marvell-config.patch \
          "
SRC_URI[md5sum] = "13073eb5314b83a31be88e4117e8bbcd"
SRC_URI[sha256sum] = "bdd3ccf31df1f3e8530887986ea8b6702a3db139486738213f5de8d8690b3723"

S = "${WORKDIR}/openswan-${PV}"

PARALLEL_MAKE = ""
EXTRA_OEMAKE = "DESTDIR=${D} \
                USERCOMPILE="${CFLAGS}" \
                FINALCONFDIR=${sysconfdir}/ipsec \
                INC_RCDEFAULT=${sysconfdir}/init.d \
                INC_USRLOCAL=${prefix} \
                INC_MANDIR=share/man WERROR=''"

do_compile () {
	oe_runmake programs
}

do_install () {
	oe_runmake install
}

FILES_${PN} = "${sysconfdir} ${libdir}/ipsec/* ${sbindir}/* ${libexecdir}/ipsec/* ${prefix}/libexec/ipsec/* /var/run /run/pluto"
FILES_${PN}-dbg += "${libdir}/ipsec/.debug ${libexecdir}/ipsec/.debug ${prefix}/libexec/ipsec/.debug"

CONFFILES_${PN} = "${sysconfdir}/ipsec/ipsec.conf"
