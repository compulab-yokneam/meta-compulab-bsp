DESCRIPTION = "CompuLab update-hosts service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=6005c78143756d7490f7b41593fb82e6"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

BRANCH ?= "update-hosts"
SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=${BRANCH}"

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

SERVICE_NAME = "update-hosts.service"

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

FILES:${PN} = "etc/* ${base_libdir}/* usr/*"
RDEPENDS:${PN} = "bash"
