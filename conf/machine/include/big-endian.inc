EXTRA_IMAGECMD_jffs2 += "--big-endian"
DEFAULTTUNE = "armv7ahfb-neon"
MACHINEOVERRIDES =. "armada38x-be:"
EXTRA_IMAGEDEPENDS_remove = "u-boot"

INHERIT += "blacklist"
PNBLACKLIST[u-boot-armada38x] = "Not supported for big endian"
