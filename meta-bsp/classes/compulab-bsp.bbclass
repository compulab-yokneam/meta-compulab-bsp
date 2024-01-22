CL_RELEASE ?= "1.0"
CL_STRING = "Compulab BSP Release"
compulab_basefiles_extra() {
    for issue_file in issue issue.net;do
	sed -e "/^${CL_STRING}/d" -i ${IMAGE_ROOTFS}/etc/$issue_file
	sed -e " 1 i $(date +"${CL_STRING} ${MACHINE} ${CL_RELEASE} %y.%m based on")" -i ${IMAGE_ROOTFS}/etc/$issue_file
    done
}
