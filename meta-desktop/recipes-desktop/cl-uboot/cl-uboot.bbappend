# Simple recipe to add desktop icon and executable to run
# CompuLab U-Boot Tool

DESCRIPTION = "CompuLab U-Boot Tool for imx8 SoC"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://cl-uboot.imx8.work \
"

S = "${WORKDIR}"

install_cl_uboot_work() {
    mv ${S}/cl-uboot.imx8.work ${S}/cl-uboot.work
    sed -i "s|##SEEK##|${IMX_BOOT_SEEK}|" ${S}/cl-uboot.work
}

do_install_prepend_cl-som-imx8() {
    install_cl_uboot_work
}

do_install_prepend_ucm-imx8m-mini() {
    install_cl_uboot_work
}

do_install_prepend_mcm-imx8m-mini() {
    install_cl_uboot_work
}

do_install_prepend_iot-gate-imx8() {
    install_cl_uboot_work
}

do_install_prepend_mcm-imx8m-nano() {
    install_cl_uboot_work
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS_${PN}_remove  = " mtd-utils u-boot-compulab "
RDEPENDS_${PN}_append  = " imx-boot mmc-utils "
COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8m-mini|mcm-imx8m-mini|iot-gate-imx8|mcm-imx8m-nano)"
