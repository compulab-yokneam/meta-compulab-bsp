FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://p2p.conf \
	file://wpa-supplicant.p2p.sample \
"

PROVIDES += "${PN}-p2p"
PACKAGES += "${PN}-p2p"
FILES_${PN}-p2p += "/opt/${PN}/*"

do_install_append () {
	install -d ${D}/opt/${PN}
	install -m 0644 ${WORKDIR}/p2p.conf ${D}/opt/${PN}/p2p.conf
	install -m 0755 ${WORKDIR}/wpa-supplicant.p2p.sample ${D}/opt/${PN}/wpa-supplicant.p2p.start
}

PROVIDES += "${PN}-p2p"
RDEPENDS_${PN} += "${PN}-p2p"
