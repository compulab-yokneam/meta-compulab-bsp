FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " file://systemd_resolved.conf "
FILES:${PN} += " ${sysconfdir}/tmpfiles.d/systemd_resolved.conf "

do_install:append() {
	install -d ${D}${sysconfdir}/tmpfiles.d
	install -m 0644 ${WORKDIR}/systemd_resolved.conf ${D}${sysconfdir}/tmpfiles.d/systemd_resolved.conf
}
