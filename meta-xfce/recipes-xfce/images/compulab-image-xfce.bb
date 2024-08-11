DESCRIPTION = "A XFCE desktop demo image."

LICENSE = "MIT"

inherit core-image
inherit features_check

REQUIRED_DISTRO_FEATURES = "x11"

IMAGE_FEATURES += " \
    debug-tweaks \
    tools-profile \
    splash \
    nfs-server \
    nfs-client \
    tools-debug \
    ssh-server-dropbear \
    tools-testapps \
    hwcodecs \
    x11-base \
    dev-pkgs \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-x11 \
    packagegroup-core-full-cmdline \
    packagegroup-tools-bluetooth \
    packagegroup-xfce-base \
    packagegroup-xfce-extended \
    packagegroup-xfce-multimedia \
    xf86-video-fbdev \
    xrdb \
"

CORE_IMAGE_EXTRA_INSTALL += " \
    imx-gpu-viv-demos \
    packagegroup-imx-tools-audio \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-tools-testapps \
    packagegroup-fsl-tools-benchmark \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
"

PACKAGE_EXCLUDE = "xfce-polkit"
