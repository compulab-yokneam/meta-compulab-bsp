# Append path for freescale layer to include bsp xorg.conf 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_cl-som-imx7 = " \
	file://xorg.conf-fbdev \
"

do_configure_cl-som-imx7 () {
	mv ${S}/xorg.conf-fbdev ${S}/xorg.conf
}
