DESCRIPTION = "Sample Access Point"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=287fdbaa05d20eab1ff638af4ccde210"

SRC_URI = " \
	file://sample.nmconnection.in \
	file://ap.sh \
	file://COPYING \
"

S = "${WORKDIR}"

do_compile() {
    :
}

do_install() {
    mkdir -p ${D}/usr/share/ap/connections/
    install -m 0600 ${S}/sample.nmconnection.in ${D}/usr/share/ap/connections
    install -m 0755 ${S}/ap.sh ${D}/usr/share/ap/ap.sh
}

pkg_postinst:${PN}() {

    AP_ACTION="add" /usr/share/ap/ap.sh

}

pkg_prerm:${PN}() {

    AP_ACTION="delete" /usr/share/ap/ap.sh

}

FILES:${PN} = " \
    /usr/* \
"

RDEPENDS:${PN} += "bash uuid-runtime"
