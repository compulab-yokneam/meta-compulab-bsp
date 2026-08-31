do_install:append() {
    if [ -n "${UUU_BOOTLOADER}" ]; then
        # Package the same tagged image that uuu_bootloader_tag deploys.
        # The first U-Boot configuration and target form the primary imx-boot
        # image selected by do_deploy.
        for uboot_config in ${UBOOT_CONFIG}; do
            boot_config_machine="imx-boot${BOOT_VARIANT}-${MACHINE}-${uboot_config}.bin"

            for target in ${IMXBOOT_TARGETS}; do
                tagged_bootloader="${D}/boot/${UUU_BOOTLOADER}.tagged"
                cp "${D}/boot/$boot_config_machine-$target" "$tagged_bootloader"
                stat -L -cUUUBURNXXOEUZX7+A-XY5601QQWWZ%sEND \
                     "$tagged_bootloader" >> "$tagged_bootloader"
                break 2
            done
        done
    fi
}
