FILESEXTRAPATHS_prepend := "${THISDIR}/compulab/imx8mq:"

include compulab/imx8mq.inc

do_configure_append () {
    oe_runmake cl-som-imx8_defconfig
}

KERNEL_MODULE_AUTOLOAD += "goodix"
KERNEL_MODULE_AUTOLOAD += "snd_soc_wm8731"

COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8)"
