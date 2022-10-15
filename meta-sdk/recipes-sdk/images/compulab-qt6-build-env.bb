DESCRIPTION = "CompuLab Qt6 Build Environment Image"
LICENSE = "MIT"

inherit populate_sdk_qt6_base

QT6_BB = "dynamic-layers/qt6-layer/recipes-fsl/images/fsl-image-qt6-validation-imx.bb"

require ${QT6_BB}
