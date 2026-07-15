# Copyright 2020-2026 NXP

SUMMARY = "Wi-Fi firmware redistributed by NXP"
DESCRIPTION = "Additional Wi-Fi firmware redistributed by NXP, \
which is not covered by linux-firmware package. Once package becomes \
available as a part of linux-firmware - it can be dropped from this \
recipe in favor of upstream."

SECTION = "kernel"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bc649096ad3928ec06a8713b8d787eac"

SRC_URI = "git://github.com/nxp-imx/imx-firmware.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "lf-6.12.34_2.1.0"
SRCREV = "2be337a7bdd129ebb5e61ff713d7941eedcfa2ff"

inherit allarch

CLEANBROKEN = "1"
ALLOW_EMPTY:${PN} = "1"

do_compile[noexec] = "1"

do_install () {
    install -d ${D}${nonarch_base_libdir}/firmware/mrvl
    install -m 0644 nxp/FwImage_8997_SD/sduart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl
    ln -s sduart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl/sdsd8997_combo_v4.bin
    ln -s sduart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl/sd8997_uapsta.bin
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/firmware/mrvl/* \
"
