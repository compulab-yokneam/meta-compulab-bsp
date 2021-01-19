FILESEXTRAPATHS_prepend := "${THISDIR}/compulab:"

SRC_URI += " \
	file://pulseaudio-compulab.conf \
	file://91-pulseaudio.rules \
	file://00-default-sample-rate.conf \
	file://user-default.pa \
"

do_install_append () {
	install -d ${D}${sysconfdir}/pulse
	install -m 0644 ${WORKDIR}/user-default.pa ${D}${sysconfdir}/pulse/user-default.pa

	install -d ${D}${sysconfdir}/pulse/daemon.conf.d
	install -m 0644 ${WORKDIR}/00-default-sample-rate.conf ${D}${sysconfdir}/pulse/daemon.conf.d/00-default-sample-rate.conf


	install -d ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets
	install -m 0644 ${WORKDIR}/pulseaudio-compulab.conf ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets/pulseaudio-compulab.conf

	install -d ${D}${base_libdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/91-pulseaudio.rules ${D}${base_libdir}/udev/rules.d/91-pulseaudio.rules

	if [ -f ${D}/usr/lib/systemd/user/pulseaudio.service ];then
		sed -i -e's/^\(ConditionUser=!root\)/#\1/g' ${D}/usr/lib/systemd/user/pulseaudio.service
	fi
}
