LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://License.txt;md5=1e492cfcb05c60002d4bee800bd9c296"

SRC_URI = "git://github.com/Infineon/eltt2.git;protocol=https;branch=master"
SRC_URI += "file://Makefile.target"

PV = "1.0+git${SRCPV}"
SRCREV = "3d55476179da9bd61c2df1ba1ef010afe27e7776"

S = "${WORKDIR}/git"

do_configure () {
	cp ${WORKDIR}/Makefile.target ${S}/
}

do_compile () {
	oe_runmake -f Makefile.target
}

do_install:append() {
    install -d ${D}${bindir}
    install -m 755 ${S}/eltt2 ${D}${bindir}/eltt2
}

FILES_${PN} = " \
	/${bindir} \
"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
