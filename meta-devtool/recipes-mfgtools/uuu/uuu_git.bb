LICENSE = "MIT & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38ec0c18112e9a92cffc4951661e85a5 \
                    file://snap/local/LICENSE;md5=414077511f90c6042e6fbaf84a6e64c0"

SRC_URI = "git://github.com/NXPmicro/mfgtools.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "fec8bbbc1ac8ab36eb8f2fc8c3264d8046cbe180"

S = "${WORKDIR}/git"

DEPENDS = "openssl zlib libusb libzip"

inherit cmake pkgconfig deploy

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""
