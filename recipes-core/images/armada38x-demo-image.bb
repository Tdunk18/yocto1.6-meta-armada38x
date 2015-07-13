SUMMARY = "Armada38x demo: NAT routing and iperf testing"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-openssh"

inherit core-image

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL} \
                 devmem2 devmem3 perf lttng-tools pptp-linux rp-pppoe xfsprogs iputils xfsprogs \
                 btrfs-tools util-linux-readprofile wireless-tools trace-cmd cryptsetup coreutils \
                 dosfstools dt e2fsprogs fftw hostap-daemon iproute2 libpcap nfs-utils \
                 openssh openssl rpcbind python-core sg3-utils squashfs-tools \
                 valgrind samba openswan wireshark testfloat iperf perf \
                 iptables dnsmasq xl2tpd openswan trace-cmd  \
                 lttng-modules lttng-tools lttng-ust gdb mdadm e2fsprogs pptp-linux rp-pppoe \
                 xfsprogs iputils btrfs-tools devmem2 devmem3 util-linux \
"

IMAGE_INSTALL += "netbase dbus gnutls-openssl xfsprogs-fsck kernel-module-lttng-probe-timer \
ethtool expat lighttpd-module-indexfile libhandle shadow-securetty strace btrfs-tools \
util-linux-umount libnfsidmap kernel-module-lttng-probe-vmscan util-linux-fdisk iperf \
liberation-fonts libxcb-render gtk+ libpcre packagegroup-core-full-cmdline-utils harfbuzz \
ncurses lighttpd-module-staticfile psplash-default libkmod openssl-conf libacl \
kernel-module-lttng-probe-sched rpcbind busybox-syslog sysvinit-pidof kernel-module-lttng-probe-kmem \
cairo lttng-tools libxft gdk-pixbuf-loader-xpm procps libwrap eglibc-gconv-iso8859-1 make \
kernel-module-lttng-probe-irq xl2tpd trace-cmd libcomerr fontconfig-utils busybox \
gdk-pixbuf-loader-gif python-lang tzdata kernel-module-lttng-probe-jbd sed util-linux-mount \
libxcomposite packagegroup-core-full-cmdline pptp-linux makedevs libext2fs libbz2 \
kernel-module-lttng-probe-gpio python-math python-dbus e2fsprogs-e2fsck u-boot-script sysvinit \
gnutls eglibc-mtrace nfs-utils-client util-linux-swaponoff kernel-base fontconfig glib-2.0 \
devmem2 kernel-image openssh-scp python-textutils kernel-module-x-tables pixman sysfsutils ed \
nfs-utils lighttpd-module-access bc xfsprogs findutils samba pango-module-basic-fc lvm2 \
libtasn1 cronie xfsprogs-mkfs python-crypt gdb mc libe2p kernel-module-lttng-probe-udp m4 \
base-passwd iputils-ping6 shared-mime-info psmisc python-core kernel-module-lttng-probe-statedump \
kernel-module-lttng-probe-jbd2 util-linux-mkfs sysvinit-inittab gdk-pixbuf util-linux-libmount \
cpio lighttpd-module-accesslog kernel-devicetree packagegroup-core-full-cmdline-initscripts \
eglibc-thread-db iputils-ping elfutils lttng-modules libss kernel-module-lttng-probe-ext3 \
libxcb-shm openssh-sshd lighttpd-module-dirlisting libxdamage e2fsprogs-badblocks mc-fish \
iputils-tracepath util-linux-libuuid busybox-udhcpc psplash libwbclient ncurses-libncurses libstdc++ \
gawk-common openssl kernel-module-lttng-probe-skb libxcb libpng kernel-module-lttng-probe-workqueue \
ppp-oe ncurses-libncursesw rp-pppoe perl kernel-module-lttng-ftrace libevent \
packagegroup-core-full-cmdline-extended kernel-module-lttng-statedump libasm libtdb binutils gawk \
iproute2 dbus-lib openssh-ssh sudo util-linux-cfdisk kernel-module-lttng-probe-sunrpc util-linux-sfdisk \
libpcap modutils-initscripts devmem3 dnsmasq udev-utils kernel-module-ip-tables diffutils libxau \
libssl libgcrypt pciutils libffi grep liburcu tar eglibc-gconv kernel-module-lttng-probe-printk \
attr kernel-module-lttng-tracer base-files libcrypto libpci gdk-pixbuf-loader-jpeg u-boot-script-dev \
packagegroup-core-full-cmdline-sys-services popt packagegroup-core-full-cmdline-dev-utils \
libtirpc kernel-module-lttng-probe-rcu util-linux-losetup pango patch libgpg-error fuser \
kernel-module-lttng-probe-napi iputils-tracepath6 lttng-ust pciutils-ids gzip e2fsprogs-mke2fs \
libdw gmp mc-helpers-perl iputils-arping openssh-keygen eglibc kernel-module-lttng-probe-block \
run-postinsts lzo packagegroup-core-full-cmdline-libs freetype e2fsprogs tcp-wrappers zlib \
ncurses-terminfo gdbserver kernel-module-lttng-probe-sock \
kernel-module-lttng-ring-buffer-metadata-mmap-client mc-helpers packagegroup-core-full-cmdline-multiuser \
python-xml openswan kmod init-ifupdown kernel-module-lttng-ring-buffer-client-mmap-discard \
util-linux-libblkid kernel-module-lttng-probe-btrfs libpython2 flex mktemp initscripts libelf \
logrotate bzip2 python-codecs iputils readline udev-cache mc-helpers-python at bash cracklib \
kernel-module-lttng-probe-net util-linux-readprofile libxext mdadm byacc python-threading \
libgcc pstree kernel-module-lttng-probe-module pax perl-lib update-rc.d busybox-hwclock dbus-glib \
packagegroup-core-boot less libxcursor libxfixes shadow lighttpd libxml2 packagegroup-core-tools-debug \
python-readline file kernel-module-lttng-probe-random jpeg libsysfs kernel-module-lttng-types \
util-linux python-elementtree atk python-stringold packagegroup-core-ssh-openssh openssh \
kernel-module-lttng-probe-ext4 kernel-module-lttng-ring-buffer-client-discard \
kernel-module-lttng-probe-signal net-tools kernel-module-lttng-lib-ring-buffer time \
ncurses-terminfo-base coreutils kernel-module-lttng-probe-compaction kernel-module-iptable-filter \
udev perf ppp python-logging iputils-traceroute6 libattr python-io iptables update-alternatives-opkg \
libx11 python-re kernel-module-lttng-probe-power kernel-module-lttng-probe-scsi gdk-pixbuf-loader-png \
acl libxrandr kernel-module-lttng-ring-buffer-client-mmap-overwrite sysklogd \
kernel-module-lttng-ring-buffer-client-overwrite slang kernel-module-lttng-ring-buffer-metadata-client \
ncurses-libtinfo libxrender initscripts-functions python-pickle libxdmcp cryptsetup killall \
openssh-sftp pure-ftpd openssh-sftp-server proftpd libcap kernel-modules \
"

# Added FTP services 
#
IMAGE_INSTALL += "netbase dbus gnutls-openssl xfsprogs-fsck kernel-module-lttng-probe-timer ethtool expat lighttpd-module-indexfile libhandle shadow-securetty strace btrfs-tools util-linux-umount libnfsidmap kernel-module-lttng-probe-vmscan util-linux-fdisk iperf liberation-fonts libxcb-render gtk+ libpcre packagegroup-core-full-cmdline-utils harfbuzz ncurses lighttpd-module-staticfile psplash-default libkmod openssl-conf libacl kernel-module-lttng-probe-sched rpcbind busybox-syslog sysvinit-pidof kernel-module-lttng-probe-kmem cairo lttng-tools libxft gdk-pixbuf-loader-xpm procps libwrap eglibc-gconv-iso8859-1 make kernel-module-lttng-probe-irq xl2tpd trace-cmd libcomerr fontconfig-utils busybox gdk-pixbuf-loader-gif python-lang tzdata kernel-module-lttng-probe-jbd sed util-linux-mount locale-base-en-gb libxcomposite packagegroup-core-full-cmdline pptp-linux makedevs libext2fs geany libbz2 kernel-module-lttng-probe-gpio python-math python-dbus e2fsprogs-e2fsck u-boot-script sysvinit gnutls eglibc-mtrace nfs-utils-client util-linux-swaponoff kernel-base fontconfig glib-2.0 devmem2 kernel-image openssh-scp python-textutils kernel-module-x-tables pixman sysfsutils ed nfs-utils lighttpd-module-access bc xfsprogs findutils samba pango-module-basic-fc libtasn1 cronie xfsprogs-mkfs python-crypt gdb mc libe2p kernel-module-lttng-probe-udp m4 base-passwd eglibc-binary-localedata-en-us iputils-ping6 shared-mime-info psmisc python-core kernel-module-lttng-probe-statedump kernel-module-lttng-probe-jbd2 util-linux-mkfs sysvinit-inittab gdk-pixbuf util-linux-libmount cpio lighttpd-module-accesslog kernel-devicetree packagegroup-core-full-cmdline-initscripts eglibc-thread-db iputils-ping elfutils lttng-modules libss kernel-module-lttng-probe-ext3 libxcb-shm openssh-sshd lighttpd-module-dirlisting libxdamage e2fsprogs-badblocks mc-fish iputils-tracepath util-linux-libuuid busybox-udhcpc psplash libwbclient ncurses-libncurses libstdc++ gawk-common openssl kernel-module-lttng-probe-skb libxcb libpng kernel-module-lttng-probe-workqueue ppp-oe ncurses-libncursesw rp-pppoe perl kernel-module-lttng-ftrace libevent packagegroup-core-full-cmdline-extended kernel-module-lttng-statedump libasm libtdb binutils gawk iproute2 dbus-lib openssh-ssh sudo util-linux-cfdisk kernel-module-lttng-probe-sunrpc util-linux-sfdisk libpcap modutils-initscripts devmem3 dnsmasq udev-utils kernel-module-ip-tables diffutils libxau libssl libgcrypt pciutils libffi grep liburcu tar eglibc-gconv kernel-module-lttng-probe-printk attr kernel-module-lttng-tracer base-files libcrypto libpci gdk-pixbuf-loader-jpeg u-boot-script-dev packagegroup-core-full-cmdline-sys-services popt packagegroup-core-full-cmdline-dev-utils libtirpc kernel-module-lttng-probe-rcu pure-ftpd util-linux-losetup pango patch libgpg-error fuser kernel-module-lttng-probe-napi iputils-tracepath6 lttng-ust pciutils-ids gzip e2fsprogs-mke2fs libdw gmp mc-helpers-perl iputils-arping openssh-keygen eglibc kernel-module-lttng-probe-block run-postinsts lzo packagegroup-core-full-cmdline-libs freetype e2fsprogs tcp-wrappers zlib ncurses-terminfo gdbserver kernel-module-lttng-probe-sock kernel-module-lttng-ring-buffer-metadata-mmap-client mc-helpers packagegroup-core-full-cmdline-multiuser python-xml openswan kmod init-ifupdown kernel-module-lttng-ring-buffer-client-mmap-discard util-linux-libblkid kernel-module-lttng-probe-btrfs libpython2 flex mktemp initscripts libelf logrotate bzip2 python-codecs iputils readline udev-cache mc-helpers-python at bash cracklib kernel-module-lttng-probe-net util-linux-readprofile libxext mdadm byacc python-threading libgcc pstree kernel-module-lttng-probe-module pax perl-lib update-rc.d busybox-hwclock dbus-glib packagegroup-core-boot less libxcursor libxfixes shadow lighttpd libxml2 packagegroup-core-tools-debug python-readline file kernel-module-lttng-probe-random jpeg libsysfs eglibc-binary-localedata-en-gb kernel-module-lttng-types util-linux python-elementtree atk python-stringold packagegroup-core-ssh-openssh locale-base-en-us openssh kernel-module-lttng-probe-ext4 kernel-module-lttng-ring-buffer-client-discard kernel-module-lttng-probe-signal net-tools kernel-module-lttng-lib-ring-buffer time ncurses-terminfo-base coreutils kernel-module-lttng-probe-compaction kernel-module-iptable-filter udev perf ppp python-logging iputils-traceroute6 libattr python-io iptables update-alternatives-opkg libx11 python-re kernel-module-lttng-probe-power kernel-module-lttng-probe-scsi gdk-pixbuf-loader-png acl libxrandr kernel-module-lttng-ring-buffer-client-mmap-overwrite sysklogd kernel-module-lttng-ring-buffer-client-overwrite slang kernel-module-lttng-ring-buffer-metadata-client ncurses-libtinfo libxrender initscripts-functions python-pickle libxdmcp killall libcap "

# Add sysstat to get acess to "sar"

IMAGE_INSTALL += "netbase dbus gnutls-openssl xfsprogs-fsck kernel-module-algif-skcipher kernel-module-ebt-mark-m kernel-module-lttng-probe-timer ethtool expat lighttpd-module-indexfile libhandle shadow-securetty strace btrfs-tools kernel-module-ebtable-broute util-linux-umount libnfsidmap kernel-module-lttng-probe-vmscan util-linux-fdisk iperf liberation-fonts libxcb-render gtk+ libpcre packagegroup-core-full-cmdline-utils kernel-module-led-class harfbuzz kernel-module-ebtables ncurses lighttpd-module-staticfile kernel-module-ebtable-nat psplash-default libkmod openssl-conf libacl kernel-module-lttng-probe-sched rpcbind busybox-syslog sysvinit-pidof kernel-module-lttng-probe-kmem cairo lttng-tools libxft gdk-pixbuf-loader-xpm procps libwrap eglibc-gconv-iso8859-1 openssh-sftp make kernel-module-lttng-probe-irq xl2tpd trace-cmd libcomerr fontconfig-utils busybox gdk-pixbuf-loader-gif python-lang tzdata kernel-module-lttng-probe-jbd sed util-linux-mount locale-base-en-gb libxcomposite packagegroup-core-full-cmdline pptp-linux makedevs libext2fs geany libbz2 kernel-module-ebt-802-3 kernel-module-ebt-arpreply kernel-module-lttng-probe-gpio python-math python-dbus e2fsprogs-e2fsck u-boot-script sysvinit gnutls eglibc-mtrace nfs-utils-client util-linux-swaponoff kernel-base fontconfig glib-2.0 kernel-module-ebt-stp devmem2 kernel-image kernel-module-ansi-cprng openssh-scp python-textutils kernel-module-x-tables pixman sysfsutils kernel-module-udf ed nfs-utils lighttpd-module-access bc xfsprogs findutils samba pango-module-basic-fc lvm2 libtasn1 cronie xfsprogs-mkfs python-crypt gdb mc libe2p kernel-module-lttng-probe-udp m4 kernel-module-ebt-redirect base-passwd kernel-module-ebt-nflog eglibc-binary-localedata-en-us kernel-module-ebt-ip iputils-ping6 shared-mime-info psmisc python-core kernel-module-lttng-probe-statedump kernel-module-lttng-probe-jbd2 util-linux-mkfs kernel-module-ebt-pkttype sysvinit-inittab gdk-pixbuf util-linux-libmount cpio lighttpd-module-accesslog kernel-devicetree packagegroup-core-full-cmdline-initscripts eglibc-thread-db iputils-ping elfutils lttng-modules libss kernel-module-lttng-probe-ext3 libxcb-shm openssh-sshd lighttpd-module-dirlisting libxdamage e2fsprogs-badblocks mc-fish iputils-tracepath util-linux-libuuid busybox-udhcpc psplash libwbclient ncurses-libncurses libstdc++ gawk-common openssl kernel-module-lttng-probe-skb libxcb libpng kernel-module-lttng-probe-workqueue ppp-oe ncurses-libncursesw rp-pppoe kernel-module-ebt-ulog perl kernel-module-lttng-ftrace libevent packagegroup-core-full-cmdline-extended kernel-module-lttng-statedump libasm libtdb binutils gawk iproute2 dbus-lib openssh-ssh sudo util-linux-cfdisk kernel-module-lttng-probe-sunrpc util-linux-sfdisk libpcap modutils-initscripts devmem3 dnsmasq udev-utils kernel-module-ip-tables diffutils kernel-modules libxau libssl libgcrypt pciutils libffi kernel-module-ebt-dnat grep liburcu tar kernel-module-crc-itu-t eglibc-gconv kernel-module-lttng-probe-printk attr kernel-module-lttng-tracer base-files libcrypto libpci gdk-pixbuf-loader-jpeg u-boot-script-dev packagegroup-core-full-cmdline-sys-services popt packagegroup-core-full-cmdline-dev-utils kernel-module-ebt-arp libtirpc kernel-module-lttng-probe-rcu kernel-module-af-alg pure-ftpd util-linux-losetup pango patch libgpg-error fuser kernel-module-lttng-probe-napi iputils-tracepath6 lttng-ust pciutils-ids gzip e2fsprogs-mke2fs libdw gmp mc-helpers-perl iputils-arping openssh-keygen eglibc kernel-module-lttng-probe-block run-postinsts lzo packagegroup-core-full-cmdline-libs freetype e2fsprogs tcp-wrappers zlib ncurses-terminfo kernel-module-ebt-log gdbserver kernel-module-lttng-probe-sock kernel-module-lttng-ring-buffer-metadata-mmap-client mc-helpers packagegroup-core-full-cmdline-multiuser python-xml openswan kmod kernel-module-xt-tcpudp init-ifupdown kernel-module-lttng-ring-buffer-client-mmap-discard util-linux-libblkid kernel-module-lttng-probe-btrfs libpython2 flex mktemp initscripts libelf logrotate bzip2 python-codecs iputils readline udev-cache kernel-module-ebt-ip6 mc-helpers-python kernel-module-ebt-limit at bash cracklib kernel-module-lttng-probe-net util-linux-readprofile libxext mdadm byacc kernel-module-ebtable-filter python-threading libgcc pstree kernel-module-lttng-probe-module pax perl-lib update-rc.d busybox-hwclock dbus-glib packagegroup-core-boot less libxcursor libxfixes shadow lighttpd libxml2 packagegroup-core-tools-debug python-readline file kernel-module-lttng-probe-random jpeg libsysfs kernel-module-rng-core eglibc-binary-localedata-en-gb kernel-module-lttng-types util-linux python-elementtree atk python-stringold packagegroup-core-ssh-openssh locale-base-en-us openssh kernel-module-lttng-probe-ext4 kernel-module-lttng-ring-buffer-client-discard kernel-module-lttng-probe-signal net-tools kernel-module-lttng-lib-ring-buffer openssh-sftp-server time ncurses-terminfo-base coreutils kernel-module-lttng-probe-compaction kernel-module-ebt-snat kernel-module-iptable-filter udev kernel-module-ebt-mark perf ppp python-logging proftpd iputils-traceroute6 kernel-module-ebt-vlan libattr python-io iptables kernel-module-ebt-among update-alternatives-opkg libx11 python-re kernel-module-lttng-probe-power kernel-module-lttng-probe-scsi gdk-pixbuf-loader-png acl libxrandr kernel-module-lttng-ring-buffer-client-mmap-overwrite sysklogd kernel-module-lttng-ring-buffer-client-overwrite slang sysstat kernel-module-lttng-ring-buffer-metadata-client ncurses-libtinfo libxrender initscripts-functions python-pickle libxdmcp cryptsetup killall libcap "

ROOTFS_POSTPROCESS_COMMAND += "rfs_overlay; "

rfs_overlay() {
  [ -e ${THISDIR}/${PN}/rfs_overlay.tar.bz2 ] && \
    tar xf ${THISDIR}/${PN}/rfs_overlay.tar.bz2 -C ${IMAGE_ROOTFS}
}
