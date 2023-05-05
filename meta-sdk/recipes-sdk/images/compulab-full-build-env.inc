DESCRIPTION = "CompuLab Qt6 Build Environment Image"
LICENSE = "MIT"

inherit populate_sdk populate_sdk_qt6_base

QT6_BB = "dynamic-layers/qt6-layer/recipes-fsl/images/fsl-image-qt6-validation-imx.bb"

require ${QT6_BB}

TOOLCHAIN_HOST_TASK:append = " nativesdk-packagegroup-qt6-toolchain-host"
TOOLCHAIN_TARGET_TASK:append = " packagegroup-qt6-modules"
