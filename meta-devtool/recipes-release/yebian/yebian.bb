LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/compulab-yokneam/yebian.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_compile () {
YEBIAN=${DEPLOY_DIR_IMAGE}/yebian
CONF=conf SCRIPTS=scripts
mkdir -p ${YEBIAN}/${CONF} ${YEBIAN}/${SCRIPTS}

IMX_BOOT_SEEK=${IMX_BOOT_SEEK:-1}

rc=$(echo ${MACHINE} | awk -F "imx8" '{ print NF }')
if [ ${rc} -eq 2 ]; then
IMX_BOOT_PATT=imx-boot
else
IMX_BOOT_PATT=u-boot
fi

cat << eof > ${YEBIAN}/${CONF}/local.conf
YEBIAN=${YEBIAN}
DEPLOY_DIR=${DEPLOY_DIR}
DEPLOY_DIR_IMAGE=${DEPLOY_DIR_IMAGE}
MACHINE=${MACHINE}
YOCTO_LIST=${YEBIAN}/${CONF}/yocto.list
IMX_BOOT_SEEK=${IMX_BOOT_SEEK}
IMX_BOOT_PATT=${IMX_BOOT_PATT}
eof

ls -tr ${DEPLOY_DIR}/deb | awk '($0="#deb [trusted=yes] http://localhost:5678/"$0" /")' > ${YEBIAN}/${CONF}/yocto.list
cp -a ${S}/* ${YEBIAN}/${SCRIPTS}
}

RDEPENDS_${PN} = " kernel kernel-modules kernel-devicetree cl-uboot cl-deploy "
