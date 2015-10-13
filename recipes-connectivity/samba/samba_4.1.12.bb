SUMMARY = "Samba is the standard Windows interoperability suite of programs for Linux and Unix."
SECTION = "console/network"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "heimdal-native bash readline virtual/libiconv zlib popt python talloc"

PV = "4.1.12"
SRCREV = "5d9f4f950e5c63d30611e77eb7bc7fcfcf985b82"

SRC_URI = "http://download.samba.org/pub/samba/stable/samba-4.1.12.tar.gz \
		   file://samba4-0003-build-find-FILE_OFFSET_BITS-via-array.patch \
		   file://samba4-0004-build-allow-some-python-variable-overrides.patch \
		   file://samba4-0005-build-find-blkcnt_t-size-via-array.patch \
		   file://samba4-0006-build-unify-and-fix-endian-tests.patch \
		   file://samba4-0007-build-make-wafsamba-CHECK_SIZEOF-cross-compile-friendl.patch \
		   file://samba4-0008-build-tweak-SIZEOF-utmp-ut_line.patch \
		   file://samba4-0009-disable-libbsd.patch \
		   file://samba4-0020-splice-support.patch \
		   file://samba4-0021-NT1-improvements.patch \
		   file://18-avoid-get-config-by-native-ncurses.patch \
           file://cross-answers.txt \
		   file://init.samba \
		   file://init.winbind \
		   file://nmb.service \
		   file://smb.conf \
		   file://smb.service \
		   file://volatiles.03_samba \
		   file://winbind.service \
          "
SRC_URI[md5sum] = "232016d7581a1ba11e991ec2674553c4"
SRC_URI[sha256sum] = "033604674936bf5c77d7df299b0626052b84a41505a6a6afe902f6274fc29898"

S = "${WORKDIR}/samba-4.1.12"

inherit waf-samba

inherit perlnative pythonnative update-rc.d systemd

SAMBA4_IDMAP_MODULES = "idmap_ad,idmap_rid,idmap_adex,idmap_hash,idmap_tdb2"
SAMBA4_PDB_MODULES = "pdb_tdbsam,pdb_ldap,pdb_ads,pdb_smbpasswd,pdb_wbc_sam,pdb_samba4"
SAMBA4_AUTH_MODULES = "auth_unix,auth_wbc,auth_server,auth_netlogond,auth_script,auth_samba4"
SAMBA4_MODULES = "${SAMBA4_IDMAP_MODULES},${SAMBA4_PDB_MODULES},${SAMBA4_AUTH_MODULES}"

EXTRA_OECONF = " \
                --disable-glusterfs \
                --disable-iprint \
                --disable-rpath \
                --disable-rpath-install \
                --enable-fhs \
                --with-syslog \
                --without-ads \
                --without-cluster-support \
                --without-ldap \
                --with-shared-modules=${SAMBA4_MODULES} \
                --with-libiconv=${STAGING_EXECPREFIXDIR} \
                --bundled-libraries='heimdal,!asn1_compile,!compile_et,!zlib,popt,!talloc,!pytalloc,!pytalloc-util' \
"

PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                 ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                 avahi regedit\
                "

PACKAGECONFIG[acl]      = "--with-acl-support  ,--without-acl-support  ,acl"
PACKAGECONFIG[aio]      = "--with-aio-support  ,--without-aio-support  ,libaio"
PACKAGECONFIG[avahi]    = "--enable-avahi      ,--disable-avahi        ,avahi"
PACKAGECONFIG[cups]     = "--enable-cups       ,--disable-cups         ,cups"
PACKAGECONFIG[fam]      = "--with-fam          ,--without-fam          ,gamin"
PACKAGECONFIG[gettext]  = "--with-gettext      ,--without-gettext      ,gettext"
PACKAGECONFIG[gnutls]   = "--enable-gnutls     ,--disable-gnutls       ,gnutls"
PACKAGECONFIG[pam]      = "--with-pam          ,--without-pam          ,libpam"
PACKAGECONFIG[regedit]  = "--with-regedit      ,--without-regedit      ,ncurses"
PACKAGECONFIG[systemd]  = "--with-systemd      ,--without-systemd      ,systemd"

PERL_VERNDORLIB = "${datadir}/perl5/vendor_perl/"

do_compile()  {
    ${S}/buildtools/bin/waf build ${PARALLEL_MAKE}
}

do_install() {
    ${S}/buildtools/bin/waf install --destdir=${D}

    # Remove sysinit script if sysvinit is not in DISTRO_FEATURES
        
    install -D -m 755 ${WORKDIR}/init.samba ${D}${sysconfdir}/init.d/samba
    install -D -m 755 ${WORKDIR}/init.winbind ${D}${sysconfdir}/init.d/winbind
    install -D -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba/smb.conf
    install -D -m 644 ${WORKDIR}/volatiles.03_samba ${D}${sysconfdir}/default/volatiles/volatiles.03_samba

    install -d ${D}/var/log/samba
    install -d ${D}/var/spool/samba

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}; then
        rm -rf ${D}${sysconfdir}/init.d/
    fi

    install -d ${D}${systemd_unitdir}/system
    for i in nmb smb winbind; do
        install -m 0644 ${WORKDIR}/$i.service ${D}${systemd_unitdir}/system
    done
    sed -e 's,@BASE_BINDIR@,${base_bindir},g' \
        -e 's,@SBINDIR@,${sbindir},g' \
        -i ${D}${systemd_unitdir}/system/*.service

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
	install -d ${D}${sysconfdir}/tmpfiles.d
	echo "d ${localstatedir}/log/samba 0755 root root -" \
            > ${D}${sysconfdir}/tmpfiles.d/99-${BPN}.conf
    fi
}

do_install_append() {
    rm -rf "${D}/home"
    rm -rf "${D}${localstatedir}/run"
}

SYSTEMD_PACKAGES = "${PN} winbind"
SYSTEMD_SERVICE_${PN} = "nmb.service smb.service"
SYSTEMD_SERVICE_winbind = "winbind.service"

INITSCRIPT_PACKAGES = "samba winbind"
INITSCRIPT_NAME_samba = "samba"
INITSCRIPT_NAME_winbind = "winbind"

# No dependencies, goes in at level 20 (NOTE: take care with the
# level, later levels put the shutdown later too - see the links
# in rc6.d, the shutdown must precede network shutdown).
INITSCRIPT_PARAMS = "defaults"

CONFFILES_${PN} = "${sysconfdir}/samba/smb.conf"

PACKAGES =+ "ctdb-tests-dbg ctdb-tests samba-python-dbg samba-python samba-perl libwbclient libwinbind libwinbind-dbg libnss-winbind winbind winbind-dbg libnetapi libtdb libsmbsharemodes libsmbclient libsmbclient-dev cifs cifs-doc swat"

RDEPENDS_ctdb-tests += "bash"
FILES_ctdb-tests = "${libdir}/ctdb-tests \
                    ${datadir}/ctdb-tests"
FILES_ctdb-tests-dbg = "${libdir}/ctdb-tests/.debug"

FILES_samba-python = "${libdir}/python*"
FILES_samba-python-dbg = "${libdir}/python*/site-packages/samba/*/.debug \
                          ${libdir}/python*/site-packages/samba/.debug \
                          ${libdir}/python*/site-packages/.debug \
                         "
PRIVATE_LIBS_samba-python = "tdb.so"

FILES_samba-perl = "${datadir}/perl*"

FILES_winbind-dbg = "${libdir}/idmap/.debug/*.so \
                     ${libdir}/security/.debug/pam_winbind.so \
"

# For some reason the shlib code doesn't pick this up leading to: /usr/sbin/smbd: error while loading shared libraries: libnetapi.so.0: cannot open shared object file: No such file or directory
RDEPENDS_${PN} += "libnetapi"

FILES_${PN} += "${libdir}/vfs/*.so \
                ${libdir}/charset/*.so \
                ${libdir}/*.dat \
                ${libdir}/auth/*.so \
                ${libdir}/security/pam_smbpass.so \
                ${libdir}/winbind_krb5*.so \
                ${libdir}/mit_samba*.so \
				/run/lock/samba \
"

FILES_${PN}-dbg += "${libdir}/vfs/.debug \
                    ${libdir}/charset/.debug \
                    ${libdir}/auth/.debug \
                    ${libdir}/security/.debug \
                    ${libdir}/samba/*/.debug \
"

PRIVATE_LIBS_${PN} += "unix.so"

FILES_libwbclient = "${libdir}/libwbclient.so.*"
FILES_libnetapi = "${libdir}/libnetapi.so.*"
FILES_libsmbsharemodes = "${libdir}/libsmbsharemodes.so.*"
FILES_libtdb = "${libdir}/libtdb.so.*"
FILES_cifs = "${base_sbindir}/mount.cifs ${base_sbindir}/umount.cifs"
FILES_cifs-doc = "${mandir}/man8/mount.cifs.8 ${mandir}/man8/umount.cifs.8"
FILES_libsmbclient = "${libdir}/libsmbclient.so.*"
FILES_libsmbclient-dev = "${libdir}/libsmbclient.so ${includedir}"
FILES_winbind = "${sbindir}/winbindd \
                 ${bindir}/wbinfo \
                 ${bindir}/ntlm_auth \
                 ${sysconfdir}/init.d/winbind \
                 ${systemd_unitdir}/system/winbind.service \
"
FILES_libwinbind = "${libdir}/idmap/*.so \
                    ${libdir}/pdb \
                    ${libdir}/gpext \
                    ${libdir}/perfcount \
                    ${libdir}/security/pam_winbind.so \
"

FILES_libnss-winbind = "${libdir}/libnss_*${SOLIBS} \
                        ${libdir}/nss_info"

FILES_swat       = "${sbindir}/swat ${datadir}/swat ${libdir}/*.msg"
