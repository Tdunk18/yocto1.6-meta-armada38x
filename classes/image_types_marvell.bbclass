inherit image_types

# Set alignment to 4MB
IMAGE_ROOTFS_ALIGNMENT = "4"

# Set image size to 1GB
SDCARD_SIZE = "1"

IMAGE_DEPENDS_sdcard = "parted-native:do_populate_sysroot \
                        dosfstools-native:do_populate_sysroot \
                        mtools-native:do_populate_sysroot \
                        virtual/kernel:do_deploy"

SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.sdcard"

SDCARD_ROOTFS = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext2"

IMAGE_CMD_sdcard () {
	# Initialize a sparse file
	dd if=/dev/zero of=${SDCARD} bs=1 count=0 seek=${SDCARD_SIZE}G

	# Create partition table
	parted -s ${SDCARD} mklabel msdos
	parted -s ${SDCARD} unit MiB mkpart primary ext2 ${IMAGE_ROOTFS_ALIGNMENT} 100%
	parted ${SDCARD} print

	# Burn Partition
	dd if=${SDCARD_ROOTFS} of=${SDCARD} conv=notrunc seek=1 bs=${IMAGE_ROOTFS_ALIGNMENT}M && sync && sync
	
	echo ${IMAGE_TYPEDEP_sdcard}
}

# The sdcard requires the rootfs filesystem to be built before using
# it so we must make this dependency explicit.
IMAGE_TYPEDEP_sdcard = "${@d.getVar('SDCARD_ROOTFS', 1).split('.')[-1]}"
