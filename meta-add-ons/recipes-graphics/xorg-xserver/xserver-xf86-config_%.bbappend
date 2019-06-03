FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://xorg.conf.mod \
"

S = "${WORKDIR}"

do_install_append() {
    mkdir -p ${D}/etc/X11/
    cp ${S}/xorg.conf.mod ${D}/etc/X11/xorg.conf
}
