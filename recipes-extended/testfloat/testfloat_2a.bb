DESCRIPTION = "A program for testing floating-point implementation"
LICENSE = "BSD-like"

LIC_FILES_CHKSUM = "file://testfloat/testfloat.txt;beginline=87;endline=95;md5=9172cf2272bf9befeef3f63aa7ff4159"

SRC_URI = " http://www.jhauser.us/arithmetic/TestFloat-2a.tar.Z;name=TestFloat \
            http://www.jhauser.us/arithmetic/SoftFloat-2b.tar.Z;name=SoftFloat \
          "
SRC_URI_append_arm = " file://testfloat-2a-arm.patch \
                       file://testfloat-2a-arm-vfp-fix.patch "

SRC_URI[TestFloat.md5sum] = "4dc889319ae1e0c5381ec511f784553a"
SRC_URI[TestFloat.sha256sum] = "84d14aa42adefbda2ec9708b42946f7fa59f93689b042684bd027863481f8e4e"
SRC_URI[SoftFloat.md5sum] = "b4a58b5c941f1a2317e4c2500086e3fa"
SRC_URI[SoftFloat.sha256sum] = "89d14b55113a2ba8cbda7011443ba1d298d381c89d939515d56c5f18f2febf81"

S = "${WORKDIR}/TestFloat-2a"

do_unpack2(){
    mv ${WORKDIR}/SoftFloat-2b/processors/* ${S}/processors/
    mv ${WORKDIR}/SoftFloat-2b/softfloat ${S}/
}
addtask do_unpack2 after do_unpack before do_patch

EXTRA_OEMAKE = "'CC=${TARGET_PREFIX}' 'CROSS_COMPILE=${TARGET_PREFIX}' 'CFLAGS=${CFLAGS} -DTEST_KERNEL_EMU'"

do_compile(){
echo herterewsresrereeeeeeeeeeeeeeeeeeeee
echo ${TARGET_PREFIX}
    oe_runmake -C softfloat/bits32/arm-linux-gcc/
    oe_runmake -C testfloat/arm-linux-gcc/ 
}

do_install(){
    install -d ${D}/${bindir}
    install testfloat/arm-linux-gcc/testfloat.exe ${D}/${bindir}/testfloat
    install testfloat/arm-linux-gcc/testsoftfloat.exe ${D}/${bindir}/testsoftfloat
}

COMPATIBLE_HOST_arm = ".*"
COMPATIBLE_HOST ?= "(none)"
