# YEBIAN

## How to:

* Update the conf/bblayers.conf. Add this layer:
```
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-devtool"
```

* Issue this command in the ${BUILDDIR}:
```
bitbake yebian
```

* Issue this script in order to create a Debian image:
```
${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.do-all.sh
```

* Deploy the created image to an sd-card. The image is at:
```
ls -al ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/images/
```
