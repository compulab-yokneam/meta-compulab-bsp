FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://watchdog.compulab \
"

do_install:append() {
    install -Dm 0644 ${WORKDIR}/watchdog.compulab ${D}${sysconfdir}/watchdog.compulab
}

