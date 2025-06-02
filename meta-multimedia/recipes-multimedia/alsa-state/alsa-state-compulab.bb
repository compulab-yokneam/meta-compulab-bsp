LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI:append = "\
  file://asound.conf \
  file://asound.state \
"

require alsa-state-compulab.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"
