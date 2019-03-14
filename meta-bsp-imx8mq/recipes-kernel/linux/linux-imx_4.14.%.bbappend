FILESEXTRAPATHS_prepend := "${THISDIR}/compulab/imx8mq:"

include compulab/imx8mq.inc

do_configure_append () {
    oe_runmake cl-som-imx8_defconfig
}

KERNEL_MODULE_AUTOLOAD += "goodix"

COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8)"
