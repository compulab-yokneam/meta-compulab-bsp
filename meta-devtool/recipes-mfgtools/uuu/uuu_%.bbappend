SRCREV = "da3cd53f056a7868fdffaa631e4e426847aec309"
PV = "1.5.182"

inherit deploy

do_deploy() {
    mkdir -p ${DEPLOY_DIR_IMAGE}/${PN}/bin
    cp ${B}/uuu/uuu ${DEPLOY_DIR_IMAGE}/${PN}/bin
}
addtask deploy after do_compile
