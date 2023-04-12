# Simple recipe to add desktop icon and executable to run
# CompuLab U-Boot Tool

DESCRIPTION = "CompuLab U-Boot Tool for imx8 SoC"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install:prepend:mx8() {
    sed -i "s|##SEEK##|${IMX_BOOT_SEEK}|" ${S}/cl-uboot.work
}

do_install:prepend:compulab-mx93() {
    sed -i "s|##SEEK##|${IMX_BOOT_SEEK}|" ${S}/cl-uboot.work
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove:mx8  = " mtd-utils u-boot-compulab "
RDEPENDS:${PN}:append:mx8  = " imx-boot mmc-utils "

RDEPENDS:${PN}:remove:compulab-mx93  = " mtd-utils u-boot-compulab "
RDEPENDS:${PN}:append:compulab-mx93  = " imx-boot mmc-utils "
