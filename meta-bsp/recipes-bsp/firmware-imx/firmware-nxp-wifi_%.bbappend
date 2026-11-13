FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://moal.conf;subdir=local-conf \
    file://btnxpuart.conf;subdir=local-conf \
"

SRC_URI:append = " https://github.com/Ezurio/Connectivity_Stack_Release_Packages/releases/download/LRD-REL-14.8.0.13/summit-nx61x-firmware-14.8.0.13.tar.bz2;name=nx61x-firmware;subdir=summit"
SRC_URI[nx61x-firmware.md5sum] = "efe0166462579d27377beaf0f14afbd2"
SRC_URI[nx61x-firmware.sha256sum] = "0ef2c4d56c7ad27c7206457f3e3de1e14fe96a6b0b64bef79fc8610dba56b825"

# The RF-test images are only available in the older NXP firmware branch.
SRC_URI:append = " git://github.com/nxp-imx/imx-firmware.git;protocol=https;branch=lf-6.1.1_1.0.0;name=iw612-rftest;subpath=nxp/FwImage_IW612_SD/IW612_SD_RFTest;destsuffix=iw612-rftest"
SRCREV_iw612-rftest = "bacbeb4789c1b13d13aab12ada29217ce8c3e905"
SRCREV_FORMAT = "default_${PN}"

SUMMIT_DIR = "${UNPACKDIR}/summit/lib/firmware"
IW612_RFTEST_DIR = "${UNPACKDIR}/iw612-rftest"
LOCAL_CONF = "${UNPACKDIR}/local-conf"

do_install_summit() {

    # Reduce the driver debug leverl
    for conf_file in wifi_prod_params.conf wifi_prod_serdev_params.conf;do
        if [ -f ${SUMMIT_DIR}/nxp/${conf_file} ];then
            sed -i 's/\(drvdbg\)=.$/\1=2/g' ${SUMMIT_DIR}//nxp/${conf_file}
        fi
    done

    install -d ${D}${nonarch_base_libdir}/firmware/summit
    install -m 0644 ${SUMMIT_DIR}/nxp/* \
        ${D}${nonarch_base_libdir}/firmware/summit/
    for _file in wifi_prod_params.conf wifi_prod_serdev_params.conf;do
        sed -i 's/nxp/summit/g' ${D}${nonarch_base_libdir}/firmware/summit/${_file}
    done
}

do_install_rgpower() {
    install -d ${D}${nonarch_base_libdir}/firmware/nxp
    install -m 0644 ${SUMMIT_DIR}/nxp/rgpower* \
        ${D}${nonarch_base_libdir}/firmware/nxp/
}

do_install_rftest() {
    install -d ${D}${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest

    install -m 0644 ${IW612_RFTEST_DIR}/*.bin.se \
        ${D}${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest/
}

do_install_conf_etc() {
    install -d ${D}/${sysconfdir}/modprobe.d
    install -m 0644 ${LOCAL_CONF}/moal.conf ${D}/${sysconfdir}/modprobe.d/moal.conf
    install -m 0644 ${LOCAL_CONF}/btnxpuart.conf ${D}/${sysconfdir}/modprobe.d/btnxpuart.conf
}

do_install_conf_lib() {
    install -m 0644 ${SUMMIT_DIR}/nxp/wifi_prod_params.conf ${D}${nonarch_base_libdir}/firmware/nxp/wifi_prod_params.conf
    install -m 0644 ${SUMMIT_DIR}/nxp/wifi_prod_serdev_params.conf ${D}${nonarch_base_libdir}/firmware/nxp/wifi_prod_serdev_params.conf
}

do_install:append() {
    do_install_summit
    do_install_rgpower
    do_install_rftest
    do_install_conf_etc
    do_install_conf_lib
}

PACKAGES =+ " \
    ${PN}-nxpiw612-sdio-rftest \
    ${PN}-nxpiw612-sdio-conf \
"

FILES:${PN}-nxpiw612-sdio:append = " \
    ${nonarch_base_libdir}/firmware/summit/* \
    ${nonarch_base_libdir}/firmware/nxp/* \
    ${nonarch_base_libdir}/firmware/nxp/wifi_prod_serdev_params.conf \
    ${nonarch_base_libdir}/firmware/nxp/wifi_prod_params.conf \
    ${nonarch_base_libdir}/firmware/nxp/rgpower* \
"

FILES:${PN}-nxpiw612-sdio-conf:append = " \
    ${sysconfdir}/modprobe.d/moal.conf \
    ${sysconfdir}/modprobe.d/btnxpuart.conf \
"

FILES:${PN}-nxpiw612-sdio-rftest:append = " \
    ${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest/ \
"

RDEPENDS:${PN}-nxpiw612-sdio:append = " ${PN}-nxpiw612-sdio-conf ${PN}-nxpiw612-sdio-rftest "

# The standalone Summit packages install the same firmware/configuration paths.
RCONFLICTS:${PN}-nxpiw612-sdio:append = " summit-nx61x-firmware summit-nx61x-1218-firmware "
RCONFLICTS:${PN}-nxpiw612-sdio-conf:append = " summit-nx61x-firmware summit-nx61x-1218-firmware "
