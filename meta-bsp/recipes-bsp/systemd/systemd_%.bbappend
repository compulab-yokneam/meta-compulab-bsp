do_ro_fix() {
    eval ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', '', 'return 0', d)}

    # Postpone the listed services start to the moment they are granted RW disk operations
    for i in systemd-rfkill systemd-backlight@ systemd-tmpfiles-setup; do
	sed -e '/^After=/ {/local-fs.target/!s/^\(.*\)$/& local-fs.target/}' -i ${D}/image/lib/systemd/system/${i}.service
    done
}

do_install_append() {
    sed  -i '/^HandlePowerKey=ignore/d' ${D}/etc/systemd/logind.conf
    do_ro_fix
}
