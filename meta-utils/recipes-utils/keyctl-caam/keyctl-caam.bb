LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://COPYING;md5=8636bd68fc00cc6a3809b7b58b45f982"

SRC_URI = "git://github.com/nxp-imx/keyctl_caam.git;protocol=https;branch=master"
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
#SRCREV = "81dc06cdb9c4d0d4ba10459d85af9a8603774948"

S = "${WORKDIR}/git"

EXTRA_OEMAKE:append = ' KEYBLOB_LOCATION=/data/caam/keys/ '

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install 'DESTDIR=${D}'
}

FILES:${PN} = " \
	/usr \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
INSANE_SKIP:${PN} += "ldflags"
