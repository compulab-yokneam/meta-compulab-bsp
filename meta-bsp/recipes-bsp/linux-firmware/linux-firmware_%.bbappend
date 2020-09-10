FILES_${PN}-ax200 += " \
       ${nonarch_base_libdir}/firmware/iwlwifi-cc-a0-48.ucode \
       ${nonarch_base_libdir}/firmware/intel/ibt-20-1-3.ddc \
       ${nonarch_base_libdir}/firmware/intel/ibt-20-1-3.sfi \
"

PACKAGES =+ " ${PN}-ax200 "
PROVIDES =+ " ${PN}-ax200 "
