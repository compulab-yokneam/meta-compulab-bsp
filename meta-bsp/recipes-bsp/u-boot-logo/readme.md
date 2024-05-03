# u-boot-logo

* Tree:
```
.
├── u-boot-compulab
│   └── compulab.bmp
└── u-boot-compulab_%.bbappend
```

* How to modify:<br>
In order to use a new splash logo, copy a new 8-bit compulab.bmp file to the same location in the meta-layer:
```
convert /path/to/new-logo-file.png -type Palette -colors 224 -depth 8 -compress none \
  -verbose BMP3:/path/to/meta-compulab-bsp/meta-bsp/recipes-bsp/u-boot-logo/u-boot-compulab/compulab.bmp
```
