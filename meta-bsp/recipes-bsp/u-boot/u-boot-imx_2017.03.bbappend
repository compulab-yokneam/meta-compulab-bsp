FILESEXTRAPATHS_prepend := "${THISDIR}/compulab/imx8mq:"

include compulab/imx8mq.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8)"
