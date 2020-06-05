FILESEXTRAPATHS_prepend := "${THISDIR}/compulab:"

FILE_LIST = " \
	file://pulseaudio-compulab.conf \
	file://91-pulseaudio.rules \
"

SRC_URI_append_mcm-imx8m-mini += "${FILE_LIST}"
SRC_URI_append_ucm-imx8m-mini += "${FILE_LIST}"
SRC_URI_append_cl-som-imx8    += "${FILE_LIST}"

do_package_fix() {
    if [ -e "${WORKDIR}/image/etc/pulse/daemon.conf" ];then
        sed -i '$a\default-sample-rate = 48000\' ${WORKDIR}/image/etc/pulse/daemon.conf
    fi

if [ -e "${WORKDIR}/image/etc/pulse/system.pa" ];then
cat << eof >> ${WORKDIR}/image/etc/pulse/system.pa
### Automatically load driver modules for Bluetooth hardware
.ifexists module-bluetooth-policy.so
load-module module-bluetooth-policy
.endif

.ifexists module-bluetooth-discover.so
load-module module-bluetooth-discover
.endif
eof
fi
}
addtask package_fix before do_package after do_install


do_install_compulab () {
	install -d ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets
	install -m 0644 ${WORKDIR}/pulseaudio-compulab.conf ${D}${datadir}/pulseaudio/alsa-mixer/profile-sets/pulseaudio-compulab.conf

	install -d ${D}${base_libdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/91-pulseaudio.rules ${D}${base_libdir}/udev/rules.d/91-pulseaudio.rules
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
