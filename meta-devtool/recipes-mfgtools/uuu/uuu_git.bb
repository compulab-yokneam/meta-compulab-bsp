LICENSE = "MIT & LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38ec0c18112e9a92cffc4951661e85a5 \
                    file://snap/local/LICENSE;md5=414077511f90c6042e6fbaf84a6e64c0"

SRC_URI = "https://github.com/NXPmicro/mfgtools.git;protocol=https"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "openssl zlib libusb libzip"
DEPENDS_class-native = "bzip2-replacement-native zlib-native libusb-native openssl-native zip-native libzip-native"

inherit cmake pkgconfig deploy

BBCLASSEXTEND = "native"

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

do_deploy() {
    mkdir -p ${DEPLOY_DIR_IMAGE}/${PN}/bin
    cp ${B}/uuu/uuu ${DEPLOY_DIR_IMAGE}/${PN}/bin
}

addtask deploy after do_compile
