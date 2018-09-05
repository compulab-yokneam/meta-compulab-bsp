# Simple recipe to add desktop icon and executable to run
# CompuLab U-Boot Tool

DESCRIPTION = "CompuLab U-Boot Tool for cl-som-imx8"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://cl-uboot.cl-som-imx8.work \
"

S = "${WORKDIR}"

do_install_prepend() {
    mv ${S}/cl-uboot.cl-som-imx8.work ${S}/cl-uboot.work
}


RDEPENDS_${PN}_remove_cl-som-imx8  = " mtd-utils u-boot-compulab "
RDEPENDS_${PN}_append_cl-som-imx8  = " imx-boot "
COMPATIBLE_MACHINE_cl-som-imx8 = "cl-som-imx8"
