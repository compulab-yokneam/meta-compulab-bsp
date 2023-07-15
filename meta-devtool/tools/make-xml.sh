[[ -n ${BUILDDIR:-""} ]] || return

_MACHINE=$(awk '(/^MACHINE/)&&($0=$NF)' ${BUILDDIR}/conf/local.conf)
_MACHINE=${_MACHINE//\'/}

declare -A _layer
_layer+=( ['iot-gate-imx8plus']='meta-bsp-imx8mp' )
_layer+=( ['som-imx8m-plus']='meta-bsp-imx8mp' )
_layer+=( ['ucm-imx8m-plus']='meta-bsp-imx8mp' )
_layer+=( ['iot-gate-imx8']='meta-bsp-imx8mm' )
_layer+=( ['mcm-imx8m-mini']='meta-bsp-imx8mm' )
_layer+=( ['ucm-imx8m-mini']='meta-bsp-imx8mm' )
_layer+=( ['ucm-imx93']='meta-bsp-imx9' )

_LAYER=${_layer[${_MACHINE}]}

_end() {
:
}

_start()
{
cat << eof
<?xml version="1.0" encoding="UTF-8" ?>
 <manifest>
 <remote fetch="https://github.com/compulab-yokneam" name="compulab"/>

eof
:
_start() {
:
}
_end() {
cat << eof

 </manifest>
eof
:
}
}

repo=""
repo+=" meta-compulab "
repo+=" meta-compulab-bsp "
repo+=" meta-compulab-uefi "
repo+=" ${_LAYER} "

repo=( ${repo} )
n=$((${#repo[@]}-1))
for _i in ${!repo[@]};do
    _repo=${repo[$_i]}
    p=${BUILDDIR}/../sources/${_repo}
    [[ -d ${p} ]] || continue
    r=$(git -C ${p} show --summary | awk '(/commit/)&&($0=$2)')
    [[ ${n} -eq ${_i} ]] && d=" " || d="/"
    _start
cat << eof
    <project name="${_repo}" remote="compulab" revision="${r}" path="sources/${_repo}"${d}>
eof
done

cat << eof
        <linkfile src="tools/compulab-setup-env" dest="compulab-setup-env"/>
    </project>
eof

_end
