DESCRIPTION = "CompuLab cl-iotg configuration tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3f507272a82d1d322416f6d7e786e729"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

SRC_URI = "git://github.com/compulab-yokneam/bin;protocol=https;branch=cl-iotg-config"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	tar -C src-cl-iotg-config --exclude="DEBIAN" -cf - . | tar -C ${D} -xf -
	if ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','true','false',d)}; then
		mkdir -p ${D}/usr
		mv ${D}/lib ${D}/usr/
	fi
	chown -R 0:0 ${D}
}

SYSTEMD_SERVICE:${PN} = "cl-iotg-config-setup.service"

FILES:${PN} = "opt/* usr/*"

RDEPENDS:${PN} = " \
    bash \
    coreutils \
    evtest \
    findutils \
    tree \
    whiptail \
"
