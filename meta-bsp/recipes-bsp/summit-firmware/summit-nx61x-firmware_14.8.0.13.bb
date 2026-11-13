LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.ezurio;md5=fd3dd0630b215465b6f50540642d5b93 \
                    file://LICENSE.nxp2;md5=7b112d07b0616149941639c05f68f431"

SRC_URI = "https://github.com/Ezurio/Connectivity_Stack_Release_Packages/releases/download/LRD-REL-${PV}/summit-nx61x-firmware-${PV}.tar.bz2;subdir=summit"
SRC_URI[sha256sum] = "0ef2c4d56c7ad27c7206457f3e3de1e14fe96a6b0b64bef79fc8610dba56b825"

SRC_URI:append = " \
    file://moal.conf;subdir=local-conf \
    file://btnxpuart.conf;subdir=local-conf \
"

S = "${UNPACKDIR}/summit"

RCONFLICTS:${PN} = " \
    summit-nx61x-1218-firmware \
    firmware-nxp-wifi-nxpiw612-sdio \
    firmware-nxp-wifi-nxpiw612-sdio-extra \
    linux-firmware-nxpiw612-sdio \
"

do_configure[noexec] = "1"

do_compile[noexec] = "1"

SUMMIT_DIR = "${S}/lib/firmware"

LOCAL_CONF = "${UNPACKDIR}/local-conf"
do_install_conf_etc() {
    install -d ${D}/${sysconfdir}/modprobe.d
    install -m 0644 ${LOCAL_CONF}/moal.conf ${D}/${sysconfdir}/modprobe.d/moal.conf
    install -m 0644 ${LOCAL_CONF}/btnxpuart.conf ${D}/${sysconfdir}/modprobe.d/btnxpuart.conf
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/firmware/nxp
    cp -R --no-preserve=ownership ${SUMMIT_DIR}/nxp/. ${D}${nonarch_base_libdir}/firmware/nxp/
    for conf_file in wifi_prod_params.conf wifi_prod_serdev_params.conf;do
        if [ -f ${D}${nonarch_base_libdir}/firmware/nxp/${conf_file} ];then
            sed -i 's/\(drvdbg\)=.$/\1=2/g' ${D}${nonarch_base_libdir}/firmware/nxp/${conf_file}
        fi
    done
    do_install_conf_etc
}

PACKAGES =+ " \
    ${PN}-conf \
"

FILES:${PN} = " \
    ${nonarch_base_libdir}/firmware/nxp/ \
"

FILES:${PN}-conf:append = " \
    ${sysconfdir}/modprobe.d/moal.conf \
    ${sysconfdir}/modprobe.d/btnxpuart.conf \
"

RDEPENDS:${PN}:append = " ${PN}-conf "
