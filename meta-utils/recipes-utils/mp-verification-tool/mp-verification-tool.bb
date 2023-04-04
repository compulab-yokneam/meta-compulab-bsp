LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://README;md5=b197a514777b5370d00d8551013392f7"

SRC_URI = "git://github.com/nxp-imx-support/imx_sec_apps.git;protocol=https;branch=master"
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/mp-verification-tool"

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}/opt/imx_sec_apps/mp-verification-tool
	install -m 0755 ${S}/verify ${D}/opt/imx_sec_apps/mp-verification-tool
}

FILES:${PN} = " \
	/opt \
"

DEPENDS += "openssl"
PACKAGE_ARCH = "${MACHINE_SOCARCH}"
INSANE_SKIP:${PN} += "ldflags"
