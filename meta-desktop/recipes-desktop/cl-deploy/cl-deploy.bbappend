PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove:aarch64 = "u-boot-fw-utils"
SRC_URI:remove:aarch64 = "file://cl-deploy.mtd"
