LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://bt-firmware/LICENCE;md5=cbc5f665d04f741f1e006d2096236ba7 \
                    file://firmware/LICENCE;md5=cbc5f665d04f741f1e006d2096236ba7 \
                    file://nvram/LICENCE;md5=cbc5f665d04f741f1e006d2096236ba7 \
                    file://nvram/LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

SRC_URI = "git://github.com/compulab-yokneam/bin.git;branch=cypress;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "b1c41c55fe183db211c8e73780673c8d82fc6d74"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${base_libdir}/firmware/brcm/bcm4339

	cd ${D}${base_libdir}/firmware/brcm/bcm4339

	install -m 0644 ${S}/nvram/brcmfmac4339-sdio.ZP.txt brcmfmac4339-sdio.txt
	install -m 0644 ${S}/firmware/cyfmac4339-sdio.bin brcmfmac4339-sdio.bin
	install -m 0644 ${S}/bt-firmware/CYW4335C0.ZP.hcd 4339.hcd

	ln -s bcm4339/brcmfmac4339-sdio.txt ../brcmfmac4339-sdio.txt
	ln -s bcm4339/brcmfmac4339-sdio.txt ../brcmfmac4339-sdio.compulab-${MACHINE}.txt
	ln -s bcm4339/brcmfmac4339-sdio.bin ../brcmfmac4339-sdio.bin
	ln -s bcm4339/4339.hcd ../4339.hcd
}

FILES_${PN} += "${base_libdir}/firmware/brcm"
PACKAGE_ARCH = "${MACHINE_ARCH}"
