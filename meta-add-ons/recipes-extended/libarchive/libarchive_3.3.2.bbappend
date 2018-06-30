FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = " \
        file://bug929.patch \
        file://CVE-2017-14166.patch \
        file://CVE-2017-14502.patch \
        file://non-recursive-extract-and-list.patch \
        "

SRC_URI_append = " \
        file://0001-libarch-patch-1.patch \
        file://0002-libarch-patch-2.patch \
        file://0003-libarch-patch-3.patch \
        file://0004-libarch-patch-4.patch \
        "
