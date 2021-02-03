LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://github.com/compulab-yokneam/bin/raw/laird/laird.tar.bz2;subdir=laird-${PV}"
SRC_URI[md5sum] = "27e2792d9a92d67a7895a8d2c7faec4a"
SRC_URI[sha1sum] = "860e3698242def27884d85af5034d98eea8ac9dc"
SRC_URI[sha256sum] = "5ce0ddbb5c6228f2d1043308e8f3690e29bf3e7f0e5da13ffbf1d3b0786dd06c"
SRC_URI[sha384sum] = "72e2f24211da81586042cd6144d96ada720f42a5f7786b2e58817eb7e7e9fe40b6a71a02bcddbd67c84f5003d63aa662"
SRC_URI[sha512sum] = "1e4d7bf693363ad0e3faa22bae2b607f96f30ee290c931c93a2938a909faa146bb70335a613829f28b40edef538b00e3bca67652ca2df5bfa9014e8160434f1a"

S = "${WORKDIR}/laird-${PV}"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	tar -C ${S} -cf - . | tar -C ${D} -xf -
	ln -fs brcmfmac4339-sdio.txt ${D}/lib/firmware/laird/brcm/brcmfmac4339-sdio.compulab-${MACHINE}.txt
	chown -R root:root ${D}
}

FILES_${PN} = "/usr/ /lib/* /etc/*"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

RDEPENDS_${PN} = "libnl libnl-genl"

INSANE_SKIP_${PN} = "ldflags"
