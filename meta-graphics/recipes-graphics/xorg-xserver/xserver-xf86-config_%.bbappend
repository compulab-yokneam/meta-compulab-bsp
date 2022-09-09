# Append path for freescale layer to include bsp xorg.conf 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
RDEPENDS_${PN} += " xf86-video-fbdev "
