LICENSE = "BSD-2-Clause & BSD-3-Clause & GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4664f499df90fc9afd843df42543fd19"

SRC_URI = "git://github.com/nxp-imx-support/imx_sec_apps.git;protocol=https;branch=master"
SRC_URI += "file://LICENSE"
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
