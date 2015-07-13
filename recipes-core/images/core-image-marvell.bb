SUMMARY = "Marvell RFS"

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL} \
                 devmem2 devmem3 perf lttng-tools pptp-linux rp-pppoe xfsprogs iputils xfsprogs \
                 btrfs-tools util-linux-readprofile wireless-tools trace-cmd cryptsetup coreutils \
                 dosfstools dt e2fsprogs fftw hostap-daemon iproute2 libpcap nfs-utils \
                 openssh openssl rpcbind python-core sg3-utils squashfs-tools \
                 valgrind samba openswan wireshark testfloat iperf \
                 dhcp-server openssh iptables dnsmasq"

LICENSE = "MIT"

inherit core-image

