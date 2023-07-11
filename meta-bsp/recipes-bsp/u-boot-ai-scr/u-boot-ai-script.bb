LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=20919b3b0882c4ea49630574a50dd4e8"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = " \
    file://README \
    file://boot.script \
"

do_compile() {
    local console="$(printf "${SERIAL_CONSOLES}" | awk -F";" '($0=$2","$1"n8")')"
    sed "s/@@CONSOLE@@/${console}/g" ${WORKDIR}/boot.script > ${WORKDIR}/boot.script.real
    mkimage -C none -A arm -T script -d ${WORKDIR}/boot.script.real ${WORKDIR}/boot.scr
}

do_install() {
    install -d ${D}/usr/share/compulab
    install -m 0644 ${WORKDIR}/boot.script.real ${D}/usr/share/compulab/boot.ai.script
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/boot.scr ${DEPLOYDIR}/boot.ai.scr
}

addtask do_deploy after do_compile before do_build

FILES:${PN} += "/usr/share/compulab/"

RPROVIDES:${PN} += "u-boot-ai-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"
