FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
  file://asound.conf \
  file://asound.state \
"

do_install_append() {

    case ${MACHINE} in
    *imx8m-mini)
        ALSA_MACHINE=climx8mmini
	;;
    *)
        ALSA_MACHINE=$(awk -v M=${MACHINE} 'BEGIN { gsub(/-/,"",M); print M }')
	;;
    esac

    sed -i "s|##MACHINE##|${ALSA_MACHINE}|g" ${WORKDIR}/asound.conf
    sed -i "s|##MACHINE##|${ALSA_MACHINE}|g" ${WORKDIR}/asound.state

    install -d ${D}/${localstatedir}/lib/alsa
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/asound.conf ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/asound.state ${D}${localstatedir}/lib/alsa
}

FILES_${PN} += "${sysconfdir}/asound.conf ${localstatedir}/lib/alsa "
CONFFILES_${PN} = "${sysconfdir}/asound.conf"
