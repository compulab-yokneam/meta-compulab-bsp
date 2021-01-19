FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
  file://asound.conf \
  file://asound.state \
"

do_install_append() {

    install -d ${D}/${localstatedir}/lib/alsa
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/asound.conf ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/*.state ${D}${localstatedir}/lib/alsa
}

FILES_${PN} += "${sysconfdir}/asound.conf ${localstatedir}/lib/alsa "
CONFFILES_${PN} = "${sysconfdir}/asound.conf"
