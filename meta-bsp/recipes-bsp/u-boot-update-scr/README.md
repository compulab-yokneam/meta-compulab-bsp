# u-boot-update-script

This is a boot.update.scr that updates the device boot loader.

* Bootloader location

CompuLad imx8mm boot loader location is at emmc boot hw partititon.

|U-Boot|Linux|
|---|---|
|mmc 2 1|/dev/mmcblk2boot0

* Update `conf/local.conf`

```
CORE_IMAGE_EXTRA_INSTALL += " u-boot-update-script "
IMAGE_BOOT_FILES:append = " boot.update.scr "
```

* How to run

Stop in u-boot and issue:
```
setenv script boot.update.scr
boot
```

