# Enable opus plug-in 
PACKAGECONFIG_append = " opus "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
    file://0001-Fix-gst-memory-leak.patch \
"


