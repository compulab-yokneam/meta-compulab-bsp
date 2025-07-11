LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-FREE.txt;md5=60cf88fcfd1f3ebe7d3780ba8bbc53b6"

SRC_URI = "https://www.modbusdriver.com/downloads/modpoll.tgz"
SRC_URI[sha256sum] = "a2990d344c3ab0af0c57aa9d0bb3942c009116a1fc78a50b7c37ad7997babd97"

S = "${UNPACKDIR}/${BPN}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	install -d ${D}/opt/modpoll/
	install -m 0755 ${S}/aarch64-linux-gnu/modpoll ${D}/opt/modpoll
}

FILES:${PN} += "opt/modpoll/*"
INSANE_SKIP:${PN} += "already-stripped"
