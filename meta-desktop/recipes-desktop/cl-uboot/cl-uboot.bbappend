# Simple recipe to add desktop icon and executable to run
# CompuLab U-Boot Tool

DESCRIPTION = "CompuLab U-Boot Tool for imx8 SoC"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install:prepend:aarch64() {
    sed -i "s|##SEEK##|${IMX_BOOT_SEEK}|" ${S}/cl-uboot.work
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove:aarch64 = " mtd-utils u-boot-compulab "
RDEPENDS:${PN}:append:aarch64 = " imx-boot mmc-utils "
