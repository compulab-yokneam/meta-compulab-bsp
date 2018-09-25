SUMMARY = "cl-som-imx8-wireless:  Wi-Fi/BT drivers and firmware for the Murata 1CX module"

LIC_FILES_CHKSUM_append_cl-som-imx8 = "file://${WORKDIR}/cyw-fmac-fw/LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

SRC_URI_append_cl-som-imx8 = " \
    git://github.com/murata-wireless/cyw-fmac-fw;protocol=git;branch=master;destsuffix=cyw-fmac-fw;name=cyw-fmac-fw \
    git://github.com/murata-wireless/cyw-fmac-nvram;protocol=git;branch=master;destsuffix=cyw-fmac-nvram;name=cyw-fmac-nvram \
    git://github.com/murata-wireless/cyw-bt-patch;protocol=git;branch=master;destsuffix=cyw-bt-patch;name=cyw-bt-patch \
    git://github.com/murata-wireless/cyw-fmac-utils-imx64;protocol=git;branch=master;destsuffix=cyw-fmac-utils-imx64;name=cyw-fmac-utils-imx64 \
"

SRCREV_cyw-fmac-fw = "9093abee83d41e4442b4695c8bb5dd8d73c01c90"
SRCREV_cyw-fmac-nvram = "d27f1bf105fa1e5b828e355793b88d4b66188411"
SRCREV_cyw-bt-patch = "748462f0b02ec4aeb500bedd60780ac51c37be31"
SRCREV_cyw-fmac-utils-imx64 = "29c50618afcdf48b8a5f038c5fcd25411348e2db"
SRCREV_FORMAT = "default+cyw-fmac-fw+cyw-fmac-nvram+cyw-bt-patch+cyw-fmac-utils-imx64"

do_install_append_cl-som-imx8() {

    install -d ${D}/lib/firmware/bcm/1CX_BCM4356/
    install -d ${D}${sysconfdir}/firmware/
    install -d ${D}${bindir}

    cp ${WORKDIR}/cyw-fmac-fw/brcmfmac4356-pcie.bin ${D}/lib/firmware/bcm/1CX_BCM4356/fw_bcmdhd.bin
    cp ${WORKDIR}/cyw-fmac-nvram/brcmfmac4356-pcie.1CX.txt ${D}/lib/firmware/bcm/1CX_BCM4356/bcmdhd.cal
    cp ${WORKDIR}/cyw-bt-patch/CYW4354A2.1CX.hcd ${D}${sysconfdir}/firmware/CYW4354A2.1CX.hcd
    cp ${WORKDIR}/cyw-bt-patch/CYW4354A2.1CX.hcd ${D}${sysconfdir}/firmware/BCM4354A2.1CX.hcd
    cp ${WORKDIR}/cyw-fmac-utils-imx64/wl ${D}${bindir}/wl

}

FILES_${PN}-bcm4356_append_cl-som-imx8 = " \
    /lib/firmware/bcm/1CX_BCM4356/fw_bcmdhd.bin \
    /lib/firmware/bcm/1CX_BCM4356/bcmdhd.cal \
    ${sysconfdir}/firmware/CYW4356A2.1DX.hcd \
    ${sysconfdir}/firmware/BCM4356A2.1DX.hcd \
"

RDEPENDS_${PN} = "libnl libnl-nf libnl-route libnl-genl"
