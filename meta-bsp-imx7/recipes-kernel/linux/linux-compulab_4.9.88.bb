SUMMARY = "CompuLab 4.9.88 kernel"
DESCRIPTION = "Linux kernel for CompuLab imx(X) boards."

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-imx-src.inc

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

SRCBRANCH = "imx_4.9.88_2.0.0_ga"
SRCREV = "6507266728a7806d850ce22ec923d90cf4aa4318"
LOCALVERSION = "-cl-2.0"

include linux-compulab-4.9.88/linux-compulab_cl-som-imx7.inc

addtask copy_defconfig after do_patch before do_preconfigure
do_copy_defconfig () {
    install -d ${B}
    mkdir -p ${B}
    cp ${S}/arch/arm/configs/compulab_imx7_defconfig ${B}/.config
    cp ${S}/arch/arm/configs/compulab_imx7_defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "cl-som-imx7"
EXTRA_OEMAKE_append_mx7 = " ARCH=arm"
