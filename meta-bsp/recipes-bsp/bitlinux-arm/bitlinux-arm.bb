LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://www.passmark.com/downloads/bitlinux_arm.tar.gz"
SRC_URI[sha256sum] = "4fbf1fd3fed38ea5ed1e4d5b4929976ef46377ddb567da857b9ba8b081cd5604"
SRC_URI += "https://raw.githubusercontent.com/compulab-yokneam/Documentation/refs/heads/master/PassMark/cmdline_config.txt;name=config;subdir=config"
SRC_URI[config.sha256sum] = "cfdcd051952dfc26c1c1315b9e5181886bf4814626d54bdae6f6532c8ccee667"

S = "${WORKDIR}/burnintest_arm"
S_config = "${WORKDIR}/config"

do_configure[noexec] = "1"
do_package_qa[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
	install -d ${D}/opt/${PN}
	install -m 0755 ${S}/advnet_endpoint_aarch64 ${D}/opt/${PN}/
	install -m 0755 ${S}/bit_cmd_line_aarch64 ${D}/opt/${PN}/
	install -m 0755 ${S}/memtest_aarch64 ${D}/opt/${PN}/
	install -m 0644 ${S_config}/cmdline_config.txt ${D}/opt/${PN}/
}

pkg_postinst_ontarget:${PN} () {
	rootfs=${D:-"/"}
	bin_name="${rootfs}/opt/bitlinux-arm/bit_cmd_line_aarch64"
	bin_lib=$(readelf -d ${bin_name} | awk '(/Shared library:/)&&(/libncurses/)&&($0=$NF)' | tr -d "[]")
	pkg_name=$(dpkg --root=${rootfs} -l | awk '(/libncurses[[:digit:]]/)&&(gsub(/:arm64/,"",$2))&&($0=$2)')
	dpkg --root=${rootfs} -L ${pkg_name} | awk '(/libncurses/)&&(/aarch64/)' | grep -q ${bin_lib} && rc=$? || rc=$?

	if [ ${rc} -eq 0 ];then
		echo "Ready to run with ${bin_lib} ... "
		exit 0
	fi

	lib_name=$(basename $(dpkg --root=${rootfs} -L ${pkg_name} | awk '(/libncurses.so.[[:digit:]]$/)'))

	echo "Patche is needed ..."

	patchelf --replace-needed ${bin_lib} ${lib_name} ${bin_name} || true

	echo "done"
	
	exit 0
}


RDEPENDS:${PN} += "ncurses patchelf elfutils"

FILES:${PN} += "/opt/"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

INSANE_SKIP:${PN} += "ldflags already-stripped"
