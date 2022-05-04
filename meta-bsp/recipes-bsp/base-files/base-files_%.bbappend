# Copyright (C) 2019 CompuLab LTD

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://resize.sh \
    file://pager.sh \
"
do_install:append () {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/resize.sh ${D}${sysconfdir}/profile.d/resize.sh
    install -m 0755 ${WORKDIR}/pager.sh ${D}${sysconfdir}/profile.d/pager.sh
}

FILES:${PN} += "${sysconfdir}/profile.d/resize.sh"
FILES:${PN} += "${sysconfdir}/profile.d/pager.sh"
