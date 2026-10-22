DESCRIPTION = "CompuLab rf-test interactive demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=50440fda86b8cc5dbe52a9c8654eb566"
MAINTAINER = "CompuLab <compulab@compulab.com>"

SRC_URI = "git://github.com/compulab-yokneam/bin.git;protocol=https;branch=rf-test"

PV = "1.0+git"
SRCREV = "${AUTOREV}"

# NOTE: no Makefile found, unable to determine what needs to be done

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    tar -C ${PN} --exclude="README.md" -cf - . | tar -C ${D} -xf -
    chown -R 0:0 ${D}
}

FILES:${PN} = "opt/*"
