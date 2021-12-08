# UUU

## How to build

* Update the conf/bblayers.conf. Add this layer:
```
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-devtool"
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-utils"
```

* Issue this command in the ${BUILDDIR}:
```
bitbake -k uuu-native
```

* Binary location is location:
```
${BUILDDIR}/tmp/deploy/images/${MACHINE}/uuu-native/bin/uuu
```
