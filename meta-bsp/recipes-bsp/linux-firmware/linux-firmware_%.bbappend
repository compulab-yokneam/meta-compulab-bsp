do_install:append() {
	rm -rf ${D}/lib/firmware/brcm
}

FILES:${PN}-ax200 += " \
       ${nonarch_base_libdir}/firmware/iwlwifi-cc-a0-50.ucode \
       ${nonarch_base_libdir}/firmware/iwlwifi-cc-a0-48.ucode \
       ${nonarch_base_libdir}/firmware/iwlwifi-cc-a0-46.ucode \
       ${nonarch_base_libdir}/firmware/intel/ibt-20-1-3.ddc \
       ${nonarch_base_libdir}/firmware/intel/ibt-20-1-3.sfi \
"

PACKAGES =+ " ${PN}-ax200 "
PROVIDES =+ " ${PN}-ax200 "

FILES:${PN}-ax210 += " \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0-59.ucode \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0-62.ucode \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0-63.ucode \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0-66.ucode \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0-67.ucode \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0.pnvm \
	${nonarch_base_libdir}/firmware/iwlwifi-ty-a0-gf-a0.pnvm \
	${nonarch_base_libdir}/firmware/intel/ibt-0041-0041.sfi \
	${nonarch_base_libdir}/firmware/intel/ibt-0041-0041.ddc \
"

PACKAGES =+ " ${PN}-ax210 "
PROVIDES =+ " ${PN}-ax210 "
