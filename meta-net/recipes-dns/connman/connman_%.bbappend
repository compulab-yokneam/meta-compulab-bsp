SYSTEMD_AUTO_ENABLE = "disable"

do_install:append() {
	rm -f ${D}${sysconfdir}/tmpfiles.d/connman_resolvconf.conf
}
