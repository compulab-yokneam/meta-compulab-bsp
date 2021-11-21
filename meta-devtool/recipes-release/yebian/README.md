# YEBIAN

## How to:

* Update the conf/bblayers.conf. Add this layer:
```
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-devtool"
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-utils"
```

* Issue this command in the ${BUILDDIR}:
```
bitbake yebian
```

* Issue this script in order to create a 2-partition Debian image:
```
${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```

* Issue this script in order to create a 4-partition Mender Debian image:
```
mender_image=yes ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```
|NOTE<br>This feature works if:|1) The mender feature is enabled<br>2) YEBIAN_BRANCH = "devel"|
|:---|:---|

* Deploy the created image to an sd-card. The image is at:
```
ls -al ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/images/
```

## Etc
|NOTE|Requires YEBIAN_BRANCH = "devel"|
|:---|:---|

* Image customization on Debian environment:
```
stages="deb" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```

* Image customization on CompuLab environment:
```
stages="cpl" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```

* After customization image build:
```
stages="5" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```
