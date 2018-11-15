# Append path for freescale layer to include bsp xorg.conf 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	file://xorg.conf-fbdev \
"

do_configure () {
	mv ${S}/xorg.conf-fbdev ${S}/xorg.conf
}

COMPATIBLE_MACHINE = "cl-som-imx6ul"
