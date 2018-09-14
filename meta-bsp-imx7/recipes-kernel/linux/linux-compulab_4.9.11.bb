SUMMARY = "CompuLab 4.9.11 kernel"
DESCRIPTION = "Linux kernel for CompuLab imx(X) boards."

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-imx-src.inc

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

SRCBRANCH = "imx_4.9.11_1.0.0_ga"
SRCREV = "f2ed3bfca174b3e370fc37cd5f75a67334822093"
LOCALVERSION = "-cl-2.0"

include linux-compulab-4.9.11/linux-compulab_cl-som-imx7.inc

addtask copy_defconfig after do_patch before do_preconfigure
do_copy_defconfig () {
    install -d ${B}
    mkdir -p ${B}
    cp ${S}/arch/arm/configs/compulab_imx7_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/compulab_imx7_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "cl-som-imx7"
EXTRA_OEMAKE_append_mx7 = " ARCH=arm"
