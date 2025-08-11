LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77fd2623bd5398430be5ce60489c2e81"

SRC_URI = "https://github.com/bluenviron/mediamtx/releases/download/v1.13.1/mediamtx_v1.13.1_linux_arm64.tar.gz"
SRC_URI[sha256sum] = "343c5515021df2ae7b50384412ece901b9bdb0c0e767a110ffcef562a160b416"

S = "${WORKDIR}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	install -d ${D}/opt/mediamtx
	install -m 0755 ${S}/mediamtx ${D}/opt/mediamtx/mediamtx
	install -m 0644 ${S}/mediamtx.yml ${D}/opt/mediamtx/mediamtx.yml
	install -m 0644 ${S}/LICENSE ${D}/opt/mediamtx/LICENSE
}

FILES:${PN} = "/opt/mediamtx/*"
INSANE_SKIP:${PN} += "already-stripped"
