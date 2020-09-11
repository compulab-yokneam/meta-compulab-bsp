LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = ""

do_configure () {
    # Specify any needed configure commands here
    :
}

do_compile () {
    # Specify compilation commands here
    :
}

do_deploy() {
    local RELEASE_NAME=${MACHINE}_${PN}
    local DIRS='development images kernel/dtb'
    local DESTDIR=${DEPLOY_DIR_IMAGE}/../${RELEASE_NAME}

    for dir in ${DIRS};do
        mkdir ${DESTDIR}/${dir} -p
    done

    for DEVICETREE in ${KERNEL_DEVICETREE};do
        DEVICETREE=$(basename ${DEVICETREE})
        cp -L ${DEPLOY_DIR_IMAGE}/${DEVICETREE} ${DESTDIR}/kernel/dtb/
    done

    cp -L ${DEPLOY_DIR_IMAGE}/modules-${MACHINE}.tgz ${DESTDIR}/kernel/
    cp -L ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${MACHINE}.bin ${DESTDIR}/kernel/${KERNEL_IMAGETYPE}-${MACHINE}

    type='manifest'
    for file in ${DEPLOY_DIR_IMAGE}/*${MACHINE}.${type};do
        cp -L ${file} ${DESTDIR}/images/
    done

    type='sdcard'
    for file in ${DEPLOY_DIR_IMAGE}/*${MACHINE}.${type}.*;do
        cp -L ${file} ${DESTDIR}/images/
    done

    BOOT_LOADER=$(strings ${DEPLOY_DIR_IMAGE}/u-boot.* | awk '/^U-Boot [[:digit:]]/' | head -1)
    LINUX_KERNEL=$(awk '(/kernel-image-[[:digit:]]/)&&($0=$1)' ${DESTDIR}/images/*.manifest | sort -u)

cat << eof > ${DESTDIR}/version.txt
DISTRO_VERSION: ${DISTRO_VERSION}
   BOOT_LOADER: ${BOOT_LOADER}
  LINUX_KERNEL: ${LINUX_KERNEL}
eof
    cd ${DEPLOY_DIR_IMAGE}/..
    tree ${RELEASE_NAME} -o ${RELEASE_NAME}.tree
    cd -
}

addtask deploy before do_install after do_compile

do_install () {
    # Specify install commands here
    :
}

PROVIDES_${PN} += "${PN}"
