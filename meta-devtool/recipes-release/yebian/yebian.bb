LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/compulab-yokneam/yebian.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

FEATURES = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'compulab-uefi', 'GRUB:', 'EMPTY:', d)}"

do_compile () {
YEBIAN=${DEPLOY_DIR_IMAGE}/yebian
CONF=conf SCRIPTS=scripts
mkdir -p ${YEBIAN}/${CONF} ${YEBIAN}/${SCRIPTS}

IMX_BOOT_SEEK=${IMX_BOOT_SEEK:-1}

rc=$(echo ${MACHINE} | awk -F "imx8" '{ print NF }')
if [ ${rc} -eq 2 ]; then
IMX_BOOT_PATT=imx-boot
else
IMX_BOOT_PATT=u-boot.bin
fi

cat << eof > ${YEBIAN}/${CONF}/local.conf
YEBIAN=${YEBIAN}
DEPLOY_DIR=${DEPLOY_DIR}
DEPLOY_DIR_IMAGE=${DEPLOY_DIR_IMAGE}
MACHINE=${MACHINE}
IMX_BOOT_SEEK=${IMX_BOOT_SEEK}
IMX_BOOT_PATT=${IMX_BOOT_PATT}
FEATURES=${FEATURES}
eof

cp -a ${S}/* ${YEBIAN}/${SCRIPTS}
}

RDEPENDS_${PN} = " kernel kernel-modules kernel-devicetree cl-uboot cl-deploy u-boot-fw-utils "
RDEPENDS_${PN}_ucm-imx8m-mini_append = " firmware-cypress "
RDEPENDS_${PN}_mcm-imx8m-mini_append = " firmware-cypress "
