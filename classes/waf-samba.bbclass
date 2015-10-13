# waf is a build system which is used by samba related project.
# Obtain details from https://wiki.samba.org/index.php/Waf
# 
inherit pythonnative

DEPENDS += "libxslt-native python"

CONFIGUREOPTS = " --prefix=${prefix} \
                  --bindir=${bindir} \
                  --sbindir=${sbindir} \
                  --libexecdir=${libexecdir} \
                  --datadir=${datadir} \
                  --sysconfdir=${sysconfdir} \
                  --sharedstatedir=${sharedstatedir} \
                  --localstatedir=${localstatedir} \
                  --libdir=${libdir} \
                  --includedir=${includedir} \
                  --oldincludedir=${oldincludedir} \
                  --infodir=${infodir} \
                  --mandir=${mandir} \
                "

do_configure() {
    export BUILD_SYS=${BUILD_SYS}
    export HOST_SYS=${HOST_SYS}
    export BUILD_ARCH=${BUILD_ARCH}
    export HOST_ARCH=${HOST_ARCH}
    export STAGING_LIBDIR=${STAGING_LIBDIR}
    export STAGING_INCDIR=${STAGING_INCDIR}
    export PYTHONPATH=${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}
	export python_LDFLAGS=""
	export python_LIBDIR=""

    ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF} --cross-compile --cross-answers=${WORKDIR}/cross-answers.txt
}

do_install() {
    oe_runmake install DESTDIR=${D}
}