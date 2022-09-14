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

* Bootstrap
```
cd ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/scripts
./bootsrtap.sh
cd -
```

### Configuring the build

* Issue this script in order to create a Debian build cinfiguration file:
```
${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
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
${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
```

* Issue this script in order to create a 4-partition Mender Debian image:

|NOTE|The mender is required|
|:---|:---|

```
mender_image=yes ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
```

### Look at the build results
* Deploy the created image to an sd-card. The image is at:
```
ls -al ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/images/
```

## Customization

* Image customization on Debian environment:
```
stages="debian" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
```

* Image customization on CompuLab environment:
```
stages="compulab" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
```

* After customization image build:
```
stages="5" ${BUILDDIR}/tmp/deploy/images/${MACHINE}/yebian/yebian-tools/debian.cmd
```
