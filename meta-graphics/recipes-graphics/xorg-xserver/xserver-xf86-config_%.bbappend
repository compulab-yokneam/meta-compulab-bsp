# Append path for freescale layer to include bsp xorg.conf 
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://Xorg.conf"

# meta-freescale/../xserver-xf86-config_%.bbappend wins for some reason.
# That is why thr prepend() copies Xorg.conf on top of xorg.conf.
# Quick & dirty solution. To be fixed.
do_install:prepend() {
	cp ${WORKDIR}/Xorg.conf ${WORKDIR}/xorg.conf
}

RDEPENDS:${PN} += " xf86-video-fbdev "
