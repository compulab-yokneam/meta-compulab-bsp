FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
   file://compulab.bmp \
"

do_configure:append () {
    cp -fv ${UNPACKDIR}/compulab.bmp ${S}/tools/logos/
}
