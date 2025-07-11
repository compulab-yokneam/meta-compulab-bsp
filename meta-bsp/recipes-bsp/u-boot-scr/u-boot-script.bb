LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${UNPACKDIR}/README;md5=2456088a0455a82ac9e16b007de97c03"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://boot.script \
    file://README \
"

do_compile() {
    sed "s/MACHINE/${MACHINE}/" ${UNPACKDIR}/boot.script > ${UNPACKDIR}/boot.in
    mkimage -C none -A arm -T script -d ${UNPACKDIR}/boot.in ${UNPACKDIR}/boot.scr
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${UNPACKDIR}/boot.scr ${DEPLOYDIR}
}

do_install() {
    install -d ${D}/usr/share/compulab
    install -m 0644 ${UNPACKDIR}/boot.in ${D}/usr/share/compulab/boot.script
}

addtask do_deploy after do_compile before do_build

FILES:${PN} += "/usr/share/compulab/"

RPROVIDES:${PN} += "u-boot-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
