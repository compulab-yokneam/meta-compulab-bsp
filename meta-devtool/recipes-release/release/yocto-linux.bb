LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = ""

DEPENDS += " bzip2 xz "

do_configure () {
    # Specify any needed configure commands here
    :
}

do_compile () {
    # Specify compilation commands here
    :
}

get_manifest_name() {
	ls ${DEPLOY_DIR_IMAGE}/*${MACHINE}.manifest | awk '(NR == 1 || length < length(shortest)) { shortest = $0 } END { print shortest }'
}

get_image_name() {
	local fn=$(get_manifest_name)
	fn=${fn%.*}

	for _c in bz2 xz;do
		for _f in  ${fn}*.${_c}; do
			[ ${_c} = 'xz' ] && C='xz -dc ' || C='bzip2 -dc '
			${C} ${_f} | file - | grep -q -e partition -e archive && rc=0 || rc=1
			if [ ${rc} -eq 0 ];then
				image_name="${image_name} ${_f}"
			fi
		done
	done
export image_name="${image_name}"
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

    manifest_name=$(get_manifest_name)
    if [ -n ${manifest_name} ];then
        cp -L ${manifest_name} ${DESTDIR}/images/
    fi

    image_name='' get_image_name
    for _name in ${image_name};do
        if [ -n ${_name} ];then
            cp -L ${_name} ${DESTDIR}/images/
        fi
    done

    BOOT_LOADER=$(strings ${DEPLOY_DIR_IMAGE}/u-boot.* | awk '/^U-Boot [[:digit:]]/' | head -1)
    LINUX_KERNEL=$(awk '(/kernel-image-[[:digit:]]/)&&($0=$1)' ${DESTDIR}/images/*.manifest | sort -u)

cat << eof > ${DESTDIR}/version.txt
DISTRO_VERSION:	${DISTRO_VERSION}
BOOT_LOADER:	${BOOT_LOADER}
LINUX_KERNEL:	${LINUX_KERNEL}
eof
    cd ${DEPLOY_DIR_IMAGE}/..
    tree --noreport ${RELEASE_NAME} -o ${RELEASE_NAME}.tree
    cd -
}

addtask deploy before do_install after do_compile

do_install () {
    # Specify install commands here
    :
}

PROVIDES_${PN} += "${PN}"
