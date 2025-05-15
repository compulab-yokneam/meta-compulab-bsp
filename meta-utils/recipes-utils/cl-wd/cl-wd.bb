DESCRIPTION = "CompuLab WD Service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f347997ae666df2adb6a794c7a1ec115"
MAINTAINER = "CompuLab <compulab@compulab.com>"

inherit systemd

SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=cl-wd"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/${BPN}"

do_configure () {
	:
}

do_compile () {
	:
}

do_install () {
	tar --exclude="README.md" -cf - . | tar -C ${D} -xf -
	if ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','true','false',d)}; then
		mkdir -p ${D}/usr
		mv ${D}/lib ${D}/usr/
	fi
	chown -R 0:0 ${D}
}

SERVICE_NAME = "cl-wd.service"

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

FILES:${PN} = "opt/* "
FILES:${PN}:append  = "${@bb.utils.contains('DISTRO_FEATURES','usrmerge','usr/* ','lib/* ',d)}"
RDEPENDS:${PN} = "bash coreutils"
