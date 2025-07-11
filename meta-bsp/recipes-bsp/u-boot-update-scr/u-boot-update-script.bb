LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/README;md5=dfca3ff67e8e1b399109eb93e66b3465"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS = " u-boot-mkimage-native "

SRC_URI = "\
    file://boot.script \
    file://README \
"

do_compile:imx-boot-container() {
    BOOTLOADER=$(basename $(ls ${UNPACKDIR}/recipe-sysroot/boot/imx-boot-* | head -1))
    sed "s|##BOOTLOADER##|${BOOTLOADER}|" ${UNPACKDIR}/boot.script > ${UNPACKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${UNPACKDIR}/boot.update.in ${UNPACKDIR}/boot.update.scr
}

# To fix the machineoverrides for imx7 machines
do_compile:compulab-mx7() {
    cp ${UNPACKDIR}/boot.script ${UNPACKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${UNPACKDIR}/boot.script ${UNPACKDIR}/boot.update.scr
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${UNPACKDIR}/boot.update.scr ${DEPLOYDIR}
}

do_install() {
    install -d ${D}/usr/share/compulab
    install -m 0644 ${UNPACKDIR}/boot.update.in ${D}/usr/share/compulab/boot.update.script
}

addtask do_deploy after do_compile before do_build

FILES:${PN} += "/usr/share/compulab/"

RPROVIDES:${PN} += "u-boot-update-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS:append:imx-boot-container = "imx-boot"
