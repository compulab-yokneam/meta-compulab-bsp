LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=0a18b17ae63deaa8a595035f668aebe1"

SRC_URI = "git://github.com/eembc/coremark.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "7685fd32bd7919581bfff2881a6dac6320581400"

S = "${WORKDIR}/git"
TARGET_CC_ARCH += "${LDFLAGS}"

do_configure () {
	:
}

do_compile () {
	export PORT_DIR=linux64
	oe_runmake CC="${CC}" compile
}

do_install () {
	install -d ${D}/usr/local/bin
	install -m 0755 ${S}/coremark.exe ${D}/usr/local/bin/coremark
}

FILES_${PN} = " \
    /usr/local/bin/* \
"

PROVIDES = "coremark"

PACKAGE_ARCH = "${MACHINE_ARCH}"
