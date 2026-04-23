LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://github.com/wkz/phytool.git;protocol=https;branch=master"

PV = "1.0+git"
SRCREV = "bcf23b0261aa9f352ee4b944e30e3482158640a4"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile () {
	oe_runmake LDFLAGS=" --static "
}

fakeroot do_install () {
	install -d ${D}/usr/local/bin ${D}/usr/usr/local/share
	oe_runmake install 'DESTDIR=${D}'
	chown -R root:root ${D}/usr
}

FILES:${PN} = " \
	/usr/* \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
