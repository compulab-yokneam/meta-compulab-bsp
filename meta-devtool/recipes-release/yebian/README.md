# YEBIAN

|NOTE|The tool is for CompuLab internal use only|
|:---|:---|

## How to:

### Prepare the tool
* Update the conf/bblayers.conf. Add this layer:
```
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-devtool"
BBLAYERS += " ${BSPDIR}/sources/meta-compulab-bsp/meta-utils"
```

* Issue this command in the ${BUILDDIR}:
```
bitbake yebian
```

### Configuring the build

* Issue this script in order to create a Debian build cinfiguration file:
```
${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts/debian.cmd
```
* Follow the instructions onf the screen dialog:
```
--- Debootstrap Configuration started --
conf file : [ ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/conf/debian.debootstrap.inc ]
1) debian
2) ubuntu
name  (Ctrl^C -- exit) : 1
1) buster
2) bullseye
3) sid
version  (Ctrl^C -- exit) : 3
1) arm64
2) armhf
arch  (Ctrl^C -- exit) : 2
1) buildd
2) minbase
variant  (Ctrl^C -- exit) : 2
--- Debootstrap Configuration created ---
conf file : conf file : [ ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/conf/debian.debootstrap.inc ]
        Review the created configuration;
        Re-run the main script.
```

### Issue the build

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

### Look at the build results
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

### Switch to another branch
* Set the variable `YEBIAN_BRANCH = "devel-next"` in the con/local.conf
* Issue:
```
bitbake yebian -c cleanall
```
* Remove the old yebian build folder:
```
rm -rf ${BUILDDIR}/tmp/deploy/image/${MACHINE}/yebian
```
* Issue
```
bitbake yebian
```
* Goto the [Configuring the build](#configuring-the-build)


