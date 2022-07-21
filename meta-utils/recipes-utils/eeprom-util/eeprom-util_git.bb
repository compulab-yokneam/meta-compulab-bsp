LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c1c00f9d3ed9e24fa69b932b7e7aff2"

SRC_URI = "git://github.com/compulab/eeprom-util.git;protocol=https"
SRC_URI += "file://0001-Makefile-Take-CC-from-the-environment-if-defined.patch"

PV = "1.0+git${SRCPV}"
SRCREV = "257db099e8339404510eded3521da5370b321041"

S = "${WORKDIR}/git"

do_configure () {
	oe_runmake auto_generated.h
}

do_compile () {
	oe_runmake static
}

do_install () {
	install -d ${D}/${prefix}/local/bin/
	install -m 0755 ${S}/eeprom-util ${D}/${prefix}/local/bin/
}

FILES_${PN} = " \
	/usr \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
