DESCRIPTION = "CompuLab iot-gate-imx8plus configuration tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=d64ca8f8dd2a5f3c658d5261d48ec786"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

IOTG_CFG_BRANCH ?= "iot-gate-imx8plus"
SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=${IOTG_CFG_BRANCH}"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	tar -C ${PN} --exclude="README.md" -cf - . | tar -C ${D} -xf -
	if ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','true','false',d)}; then
		mkdir -p ${D}/usr
		mv ${D}/lib ${D}/usr/
	fi
	chown -R 0:0 ${D}
}

SERVICE_NAME = "iotg-imx8plus-ie-config.service"

pkg_postinst:${PN} () {
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		systemctl $OPTS enable ${SERVICE_NAME}
	fi
}

pkg_postrm:${PN} () {
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		systemctl $OPTS disable ${SERVICE_NAME}
	fi
}

FILES:${PN} = "etc/* ${base_libdir}/* opt/* usr/*"
RDEPENDS:${PN} = "bash tree gpiod coreutils findutils"
