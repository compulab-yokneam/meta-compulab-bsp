LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

SRC_URI = "git://github.com/compulab/eeprom-util.git;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE:append = " static "

do_configure () {
	oe_runmake auto_generated.h
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}/${prefix}/local/bin/
	install -m 0755 ${S}/eeprom-util ${D}/${prefix}/local/bin/
}

FILES:${PN} = " \
	/usr \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
