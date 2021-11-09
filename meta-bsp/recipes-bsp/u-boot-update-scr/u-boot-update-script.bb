LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=dfca3ff67e8e1b399109eb93e66b3465"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "\
    file://boot.script \
    file://README \
"

do_compile_mx8() {
	BOOTLOADER=$(basename $(ls ${WORKDIR}/recipe-sysroot/boot/imx-boot-* | head -1))
	sed "s|##BOOTLOADER##|${BOOTLOADER}|" ${WORKDIR}/boot.script > ${WORKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${WORKDIR}/boot.update.in ${WORKDIR}/boot.update.scr
}

do_compile_mx7() {
    cp ${WORKDIR}/boot.script ${WORKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${WORKDIR}/boot.script ${WORKDIR}/boot.update.scr
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/boot.update.scr ${DEPLOYDIR}
}

do_install() {
    install -d ${D}/boot
    install -m 0644 ${WORKDIR}/boot.update.scr ${D}/boot/
    install -m 0644 ${WORKDIR}/boot.update.in ${D}/boot/
}

addtask do_deploy after do_compile before do_build

FILES_${PN} += "/boot/"

RPROVIDES_${PN} += "u-boot-update-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS_append_mx8 = "imx-boot"
