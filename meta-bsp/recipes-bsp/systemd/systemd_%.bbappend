do_ro_fix() {
    eval ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', '', 'return 0', d)}

    # Postpone the listed services start to the moment they are granted RW disk operations
    for i in systemd-rfkill systemd-backlight@ systemd-tmpfiles-setup; do
	sed -e '/^After=/ {/local-fs.target/!s/^\(.*\)$/& local-fs.target/}' -i ${D}/lib/systemd/system/${i}.service
    done
}

do_logind_patch() {
    if [[ -f ${D}/etc/systemd/logind.conf ]];then
        sed -i '/^HandlePowerKey=ignore/d' ${D}/etc/systemd/logind.conf
    fi
}

do_install_append() {
    do_logind_patch
    do_ro_fix
}
