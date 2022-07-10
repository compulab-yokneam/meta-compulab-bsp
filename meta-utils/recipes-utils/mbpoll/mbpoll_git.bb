LICENSE = "Unknown & GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464 \
                    file://3rdparty/getopt/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://package/win/license.txt;md5=ebdab60879c633f86bf65eab0659ef78"

SRC_URI = "git://github.com/epsilonrt/mbpoll.git;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "1968de6b92fff20f57019551b1e8776a6facf486"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS = " libmodbus "

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

