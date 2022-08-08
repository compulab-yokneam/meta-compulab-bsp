# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   usr/share/doc/initramfs-tools-core/copyright
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://usr/share/doc/initramfs-tools-core/copyright;md5=4fc01ee21c39633ec3547d25c2fd3a75"

SRC_URI = "https://github.com/compulab-yokneam/bin/raw/debian-src/initramfs-tools-core/initramfs-tools-core_${PV}.tar.bz2;subdir=${PN}-src"
SRC_URI[md5sum] = "c6c5a4dad6316c0dc7bf0b2a064b0084"
SRC_URI[sha1sum] = "e4daebee6060c5319b863179aacd41816e2ed5a4"
SRC_URI[sha256sum] = "b07c05f1cfcf24260b5329436d7aaf147ebc12e66b7485f16521bea63b9a8172"
SRC_URI[sha384sum] = "dd3065fc9e495332f04842beb94016f18050e2ede72e2ed618e1bb64878def275c1a78d70a1aeeb675f0a91ca09bf3d1"
SRC_URI[sha512sum] = "ce91439bc9f0b2f8ee28130e43ffa897dec0e8d0cbecdeda1348115091028c0c637d5a051c2f2709ae30c39de76fb23cac01b19b0823aee635d3f45d4f921683"

S = "${WORKDIR}/${PN}-src"
#
# NOTE: no Makefile found, unable to determine what needs to be done

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# Specify compilation commands here
	:
}

do_install () {
	tar -C ${S} -cf - . | tar -C ${D} -xf -
	chown -R root:root ${D}
}

FILES:${PN} = "/usr/* /etc/*"

# INSANE_SKIP:${PN} = "ldflags"

# PACKAGE_ARCH = "all"
