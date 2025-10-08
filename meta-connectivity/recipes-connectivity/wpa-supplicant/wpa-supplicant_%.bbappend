FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:imx-nxp-bsp = " file://defconfig "

do_configure:prepend:imx-nxp-bsp () {
    # Use NXP 6.12.20 version of defconfig
    cp ${UNPACKDIR}/defconfig wpa_supplicant/defconfig
}
