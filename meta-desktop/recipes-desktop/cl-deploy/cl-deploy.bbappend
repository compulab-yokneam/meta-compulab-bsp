PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS:${PN}:remove:mx8 = "u-boot-fw-utils"
SRC_URI:remove:mx8 = "file://cl-deploy.mtd"

RDEPENDS:${PN}:remove:compulab-mx93 = "u-boot-fw-utils"
SRC_URI:remove:compulab-mx93 = "file://cl-deploy.mtd"
