DESCRIPTION = "CompuLab iotdin-imx8p configuration tool"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=1bdb9479fc33f54550993021afb2952c"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=iotdin-imx8p"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${UNPACKDIR}/git"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	tar -C ${S}/${BPN} -cf - . | tar -C ${D} -xf -
	if ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','true','false',d)}; then
		mkdir -p ${D}/usr
		mv ${D}/lib ${D}/usr/
	fi
	chown -R 0:0 ${D}
}

SERVICE_NAME = "iotd-imx8p-stack-config.service iotd-imx8p-gw-config.service"

pkg_postinst:${PN} () {
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		for _S in ${SERVICE_NAME};do
			systemctl $OPTS enable ${_S}
		done
	fi
}

pkg_postrm:${PN} () {
	if [ -n "$D" ]; then
		OPTS="--root=$D"
	fi
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
		for _S in ${SERVICE_NAME};do
			systemctl $OPTS disable ${_S}
		done
	fi
}

FILES:${PN} = "etc/* ${base_libdir}/* opt/* usr/*"
RDEPENDS:${PN} = "bash tree gpiod coreutils findutils"
