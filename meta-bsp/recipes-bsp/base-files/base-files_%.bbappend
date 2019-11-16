# Copyright (C) 2019 CompuLab LTD

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://resize.sh \
"
do_install_append () {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/resize.sh ${D}${sysconfdir}/profile.d/resize.sh
}

FILES_${PN} += "${sysconfdir}/profile.d/resize.sh"
