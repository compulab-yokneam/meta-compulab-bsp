# Append path for freescale layer to include bsp xorg.conf 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://Xorg.conf"

# meta-freescale/../xserver-xf86-config_%.bbappend wins for some reason.
# That is why thr prepend() copies Xorg.conf on top of xorg.conf.
# Quick & dirty solution. To be fixed.
do_install_prepend() {
	cp ${WORKDIR}/Xorg.conf ${WORKDIR}/xorg.conf
}

RDEPENDS_${PN} += " xf86-video-fbdev "
