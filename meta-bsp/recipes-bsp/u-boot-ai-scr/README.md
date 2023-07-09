# u-boot-ai-script

* Purpose:<br>This script makes the CompuLab autoinstaller run if the boot.scr media is a removable device: an mmc or a usb.

* The running condition:<br>boot.scr media is a removable media && this media contains an empty <media:part2>/boot/auto file.

* How to use:<br>Update `conf/local.conf`

```
CORE_IMAGE_EXTRA_INSTALL += "u-boot-ai-script"
IMAGE_BOOTFILES += "boot.scr"
```
