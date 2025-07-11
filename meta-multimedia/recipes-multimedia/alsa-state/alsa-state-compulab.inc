FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_install:append() {

    case ${MACHINE} in
    ucm-imx93|ucm-imx91)
        ALSA_MACHINE=wm8962audio
	;;
    *imx8m-mini)
        ALSA_MACHINE=climx8mmini
	;;
    *)
        ALSA_MACHINE=$(awk -v M=${MACHINE} 'BEGIN { gsub(/-/,"",M); print M }')
	;;
    esac

    sed -i "s|##MACHINE##|${ALSA_MACHINE}|g" ${UNPACKDIR}/asound.conf
    sed -i "s|##MACHINE##|${ALSA_MACHINE}|g" ${UNPACKDIR}/asound.state

    install -d ${D}/${localstatedir}/lib/alsa
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/asound.conf ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/asound.state ${D}${localstatedir}/lib/alsa
}

RDEPENDS:${PN} = "alsa-utils"
FILES:${PN} += "${sysconfdir}/asound.conf ${localstatedir}/lib/alsa "
CONFFILES:${PN} = "${sysconfdir}/asound.conf"
