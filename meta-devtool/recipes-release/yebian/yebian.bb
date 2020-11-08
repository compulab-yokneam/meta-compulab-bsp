LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/compulab-yokneam/yebian.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_compile () {
YEBIAN=${DEPLOY_DIR_IMAGE}/yebian
mkdir -p ${YEBIAN}/local ${YEBIAN}/scripts

cat << eof > ${YEBIAN}/local/local.conf
YEBIAN=${YEBIAN}
DEPLOY_DIR=${DEPLOY_DIR}
DEPLOY_DIR_IMAGE=${DEPLOY_DIR_IMAGE}
MACHINE=${MACHINE}
YOCTO_LIST=${YEBIAN}/local/yocto.list
IMX_BOOT_SEEK=${IMX_BOOT_SEEK}
eof

ls -tr ${DEPLOY_DIR}/deb | awk '($0="#deb [trusted=yes] http://localhost:5678/"$0" /")' > ${YEBIAN}/local/yocto.list
cp -a ${S}/* ${YEBIAN}/scripts
}

RDEPENDS_${PN} = " kernel kernel-modules kernel-devicetree firmware-imx-sdma linux-firmware-ax200 cl-uboot cl-deploy u-boot-imx-fw-utils eeprom-util mbpoll "
