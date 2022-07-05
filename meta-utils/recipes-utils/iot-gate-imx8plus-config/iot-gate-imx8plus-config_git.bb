DESCRIPTION = "CompuLab iot-gate-imx8plus configuration tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=626e1dacff5ee3ba1b58877fed161ba0"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=iot-gate-imx8plus"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
# SRCREV = "be4a2e9d2b7274fa65dcddc67707d824bf345c67"

S = "${WORKDIR}/git/${BPN}"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	tar --exclude="README.md" -cf - . | tar -C ${D} -xf -
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

FILES:${PN} = "etc/* lib/* usr*"
RDEPENDS:${PN} = "bash"
