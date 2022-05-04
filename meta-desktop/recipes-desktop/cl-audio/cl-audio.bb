DESCRIPTION = "CompuLab Audio Config Helper"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://cl-iselect \
	file://cl-oselect \
"

S = "${WORKDIR}"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	mkdir -p ${D}/usr/local/bin/
	install -m 0755 ${S}/cl-iselect ${D}/usr/local/bin/
	install -m 0755 ${S}/cl-oselect ${D}/usr/local/bin/
	:
}

FILES:${PN} = " \
	/usr/local/bin/* \
"

RDEPENDS:${PN} = "bash"
