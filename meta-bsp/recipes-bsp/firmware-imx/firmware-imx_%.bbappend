BB_STRICT_CHECKSUM = "0"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_cl-som-imx8 = " \
	file://1CX_BCM4356_bcmdhd.cal \
"

do_install_append_cl-som-imx8 () {
    #1CX_BCM4356
    install -d ${D}${base_libdir}/firmware/bcm/1CX_BCM4356
    cp -rfv ${WORKDIR}/1CX_BCM4356_bcmdhd.cal ${D}${base_libdir}/firmware/bcm/1CX_BCM4356/bcmdhd.cal
}
FILESEXTRAPATHS_prepend_ucm-imx8 := "${THISDIR}/files:"
FILES_${PN}-brcm_append = " \
    /lib/firmware/brcm/ \
"
SRC_URI_append_ucm-imx8 += " \
    https://github.com/compulab-yokneam/bin/raw/master/rootfs/lib/firmware/brcm/480-0081.tar.bz2;protocol=https;md5sum=c6a3119dec228a6fb65642be931ee6aa;sha256sum=e5755109f263a70738f59869704351a15c846a9a3963236ba9742eb4e0e67052 \
"

do_install_append_ucm-imx8 () {
    cp -rfv ${WORKDIR}/lib/firmware/brcm ${D}${base_libdir}/firmware/
}


