# Copyright 2020-2021 NXP

SUMMARY = "Wi-Fi firmware redistributed by NXP"
DESCRIPTION = "Additional Wi-Fi firmware redistributed by NXP, \
which is not covered by linux-firmware package. Once package becomes \
available as a part of linux-firmware - it can be dropped from this \
recipe in favor of upstream."

SECTION = "kernel"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=be5ff43682ed6c57dfcbeb97651c2829"

SRC_URI = "git://github.com/nxp-imx/imx-firmware.git;protocol=https;branch=lf-5.15.71_2.2.0"

PV = "1.0"
SRCREV = "982bb10dfabfb9e7b9dc106c59a4fbb2c45bfb44"

S = "${WORKDIR}/git"

inherit allarch

CLEANBROKEN = "1"
ALLOW_EMPTY:${PN} = "1"

do_compile () {
	:
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/firmware/mrvl
    install -m 0644 nxp/FwImage_8997_SD/sdiouart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl
    ln -s sdiouart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl/sdsd8997_combo_v4.bin
    ln -s sdiouart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl/sd8997_uapsta.bin
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/firmware/mrvl/* \
"
