PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove = "u-boot-fw-utils"
SRC_URI:remove = "file://cl-deploy.mtd"
COMPATIBLE_MACHINE = "mx8"
