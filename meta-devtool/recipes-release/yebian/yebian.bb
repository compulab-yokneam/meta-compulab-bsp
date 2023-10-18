LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

YEBIAN_BRANCH ?= "master"

SRC_URI = "git://github.com/compulab-yokneam/yebian.git;protocol=https;branch=${YEBIAN_BRANCH}"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

FEATURES += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'compulab-uefi', 'GRUB:', 'EMPTY:', d)}"
FEATURES += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'mender', 'MENDER:', 'EMPTY:', d)}"
DEPENDS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'mender', 'mender-artifact-native', '', d)}"
NATIVE_TOOLS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'mender', 'mender-artifact', '', d)}"

BSP = "${IMX_DEFAULT_BSP}"
YEBIAN = "yebian"
IMX_BOOT_PATT:aarch64 = "imx-boot"
IMX_BOOT_PATT:arm = "u-boot.imx"
IMX_BOOT_SEEK ?= "1"
CL_RELEASE ?= "1.0"

do_compile () {
mkdir -p ${WORKDIR}/conf

cat << eof > ${WORKDIR}/conf/local.conf
YEBIAN=${DEPLOY_DIR_IMAGE}/${YEBIAN}
DEPLOY_DIR=${DEPLOY_DIR}
DEPLOY_DIR_IMAGE=${DEPLOY_DIR_IMAGE}
MACHINE=${MACHINE}
IMX_BOOT_SEEK=${IMX_BOOT_SEEK}
IMX_BOOT_PATT=${IMX_BOOT_PATT}
FEATURES="${FEATURES}"
BSP=${BSP}
DPKG_ARCH=${DPKG_ARCH}
DISTRO_CODENAME=${DISTRO_CODENAME}
CL_RELEASE=${CL_RELEASE}
eof

}

inherit deploy

do_deploy_native () {
    for native_tool in ${NATIVE_TOOLS};do
        mkdir -p ${DEPLOY_DIR_IMAGE}/${YEBIAN}/bin
        cp ${WORKDIR}/recipe-sysroot-native/usr/bin/${native_tool} ${DEPLOY_DIR_IMAGE}/${YEBIAN}/bin/
    done
}

do_deploy:append () {
    do_deploy_native
}

do_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}/${YEBIAN}
    cp -a ${WORKDIR}/conf ${DEPLOY_DIR_IMAGE}/${YEBIAN}/
    cp -a ${WORKDIR}/git ${DEPLOY_DIR_IMAGE}/${YEBIAN}/scripts
}

addtask deploy after do_compile

RDEPENDS:${PN} = " kernel kernel-modules kernel-devicetree cl-uboot cl-deploy u-boot-fw-utils "
RDEPENDS:${PN}:ucm-imx8m-mini:append = " firmware-cypress "
RDEPENDS:${PN}:mcm-imx8m-mini:append = " firmware-cypress "
RDEPENDS:${PN}:cl-som-imx6ul:remove  = " u-boot-fw-utils "
