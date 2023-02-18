LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d817f50dd29ab3d13d7bb18ee4b64dfc"

SRC_URI = "git://github.com/analogdevicesinc/pmbus_dpsm.git;protocol=https;branch=master"
SRC_URI += " \ 
	file://0001-fix.patch \
	file://0002-fix.patch \
"

PV = "1.0+git${SRCPV}"
SRCREV = "310a1e23519619c9d9b26a6d9f97b5a5e2a34966"

S = "${WORKDIR}/git"

inherit cmake autotools-brokensep

do_configure() {
	aclocal
	autoconf
	LIBS=-li2c ${S}/configure --host "${MACHINE_SOCARCH}"
}

do_compile() {
	autoupdate
	oe_runmake -j 16
}

do_install() {
	install -d ${D}/opt/${PN}
	install -m 0755 ${S}/src/LT_PMBusApp ${D}/opt/${PN}/
}

FILES:${PN} = "/opt/*"

DEPENDS += "i2c-tools"
RDEPENDS:${PN} = "i2c-tools"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
