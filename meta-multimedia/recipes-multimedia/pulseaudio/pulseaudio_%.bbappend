FILESEXTRAPATHS_prepend := "${THISDIR}/compulab:"

FILE_LIST = " \
	file://pulseaudio-compulab.conf \
	file://91-pulseaudio.rules \
	file://00-default-sample-rate.conf \
	file://user-default.pa \
"

SRC_URI_append_mcm-imx8m-mini += "${FILE_LIST}"
SRC_URI_append_ucm-imx8m-mini += "${FILE_LIST}"
SRC_URI_append_cl-som-imx8    += "${FILE_LIST}"

do_install_compulab () {
	install -d ${D}${sysconfdir}/pulse
	install -m 0644 ${WORKDIR}/user-default.pa ${D}${sysconfdir}/pulse/user-default.pa

	install -d ${D}${sysconfdir}/pulse/daemon.conf.d
	install -m 0644 ${WORKDIR}/00-default-sample-rate.conf ${D}${sysconfdir}/pulse/daemon.conf.d/00-default-sample-rate.conf


	install -d ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets
	install -m 0644 ${WORKDIR}/pulseaudio-compulab.conf ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets/pulseaudio-compulab.conf

	install -d ${D}${base_libdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/91-pulseaudio.rules ${D}${base_libdir}/udev/rules.d/91-pulseaudio.rules

	sed -i -e's/^\(ConditionUser=!root\)/#\1/g' ${D}/usr/lib/systemd/user/pulseaudio.service
}

do_install_append_mcm-imx8m-mini () {
	do_install_compulab
}

do_install_append_ucm-imx8m-mini () {
	do_install_compulab
}

do_install_append_cl-som-imx8 () {
	do_install_compulab
}

PACKAGE_ARCH_mx8 = "${MACHINE_SOCARCH}"
