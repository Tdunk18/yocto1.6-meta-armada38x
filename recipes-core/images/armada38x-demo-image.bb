SUMMARY = "Armada38x demo: NAT routing and iperf testing"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-openssh"

inherit core-image

IMAGE_INSTALL = " \
packagegroup-core-boot \
packagegroup-core-full-cmdline \
packagegroup-core-full-cmdline-dev-utils \
packagegroup-core-full-cmdline-extended \
packagegroup-core-full-cmdline-initscripts \
packagegroup-core-full-cmdline-libs \
packagegroup-core-full-cmdline-sys-services \
packagegroup-core-full-cmdline-utils \
packagegroup-core-ssh-openssh packagegroup-core-tools-debug \
${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL} \
acl at atk attr base-files base-passwd bash bc binutils btrfs-tools busybox \
byacc bzip2 coreutils cpio cracklib cronie cryptsetup devmem2 \
devmem3 dhcp-client diffutils dnsmasq dosfstools dt e2fsprogs e2fsprogs-e2fsck \
e2fsprogs-mke2fs elfutils ethtool expat fftw file findutils flex fuser gawk \
gdb gdbserver gmp gnutls gnutls-openssl grep gzip hdparm hostap-daemon \
iperf iproute2 iptables iputils iputils-arping iputils-ping iputils-ping6 \
iputils-tracepath iputils-tracepath6 iputils-traceroute6 \
kernel-base kernel-devicetree kernel-image kernel-modules \
killall kmod less libacl libasm libattr libbz2 libcap libcomerr libcrypto \
libdw libe2p libelf libevent libext2fs libffi libgcc libgcrypt libgpg-error \
libhandle libkmod libnfsidmap libpcap libpci libpcre libpython2 libss libssl \
libstdc++ libsysfs libtasn1 libtdb libtirpc liburcu libwbclient libwrap \
libxml2 lighttpd logrotate lttng-modules lttng-tools lttng-ust lvm2 lzo m4 \
make makedevs mc mdadm mktemp mv-cesa-tool ncurses netbase net-tools nfs-utils \
ocf-linux openssl openswan patch pax pciutils perf perl popt ppp \
pptp-linux procps proftpd psmisc psplash pstree pure-ftpd python-core \
python-crypt python-io python-lang python-logging python-math python-pickle \
python-re python-readline python-stringold python-textutils python-threading \
python-xml readline rpcbind samba sed sg3-utils shadow slang squashfs-tools \
strace sysfsutils sysklogd sysstat sysvinit tar tcp-wrappers testfloat time \
trace-cmd tzdata u-boot-script u-boot-script-dev udev util-linux valgrind \
wireless-tools xfsprogs xl2tpd zlib \
"

ROOTFS_POSTPROCESS_COMMAND += "rfs_overlay; "

rfs_overlay() {
  [ -e ${THISDIR}/${PN}/rfs_overlay.tar.bz2 ] && \
    tar xf ${THISDIR}/${PN}/rfs_overlay.tar.bz2 -C ${IMAGE_ROOTFS}
}
