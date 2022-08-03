#!/bin/bash -e

AP_CONF_FILE=/usr/share/ap/ap.conf

declare -A ap_defaults=(
    [AP_ID]="ap-hotspot"
    [AP_SSID]="AP"
    [AP_PASSWORD]="PASSWORD"
    [AP_ADDRESS]="172.16.5.1/24"
)

function ISCHROOT() {
	[[ -n ${AP_DIRECT} ]] && ( echo 1 ) || ( ischroot ; [[ $? = 1 ]] && echo 0 || echo 1 )
}

function ap_cfg() {
    # To figure out, whether the file exists -- then creat an empty file
    [[ -f ${AP_CONF_FILE} ]] || ( mkdir -p $(dirname ${AP_CONF_FILE}); > ${AP_CONF_FILE} )

    # To figure out, whether the file is empty -- then add a title line
    [[ $(stat --print=%b ${AP_CONF_FILE} ) != 0 ]] || echo "# AP Configuration file" > ${AP_CONF_FILE}

    source ${AP_CONF_FILE}

    # Set default values
    for i in ${!ap_defaults[@]};do [[ -n ${!i} ]] || sed -i "$ a ${i}=\"${ap_defaults[$i]}\"" ${AP_CONF_FILE}; done

    source ${AP_CONF_FILE}
}

function ap_add() {

    TIME=$(date +%s)
    UUID=$(uuidgen --random)

    if [ $(ISCHROOT) = "1" ];then
        sed "s/@@ID@@/${AP_ID}/g;s/@@UUID@@/${UUID}/g;s/@@TIME@@/${TIME}/g;s/@@SSID@@/${AP_SSID}/g;s/@@PASSWORD@@/${AP_PASSWORD}/g;s|@@ADDRESS@@|${AP_ADDRESS}|g" /usr/share/ap/connections/sample.nmconnection.in > /etc/NetworkManager/system-connections/${AP_ID}.nmconnection
    else
        nmcli connection show ${AP_ID} &>/dev/null || nmcli device wifi hotspot ifname wlan0 con-name ${AP_ID} ssid ${AP_SSID} password ${AP_PASSWORD}
        nmcli connection modify ${AP_ID} connection.autoconnect yes
        nmcli connection modify ${AP_ID} 802-11-wireless.ssid ${AP_SSID}
        nmcli connection modify ${AP_ID} 802-11-wireless-security.psk ${AP_PASSWORD}
        nmcli connection modify ${AP_ID} ipv4.address ${AP_ADDRESS}
    fi
}

function ap_del() {
    if [ $(ISCHROOT) = "1" ];then
    	rm -rf /etc/NetworkManager/system-connections/${AP_ID}.nmconnection
    else
        nmcli connection delete ${AP_ID}
    fi	    
    rm -rf ${AP_CONF_FILE}
}

ap_cfg

AP_ACTION=${AP_ACTION:-add}

[[ ${AP_ACTION} = "add" ]] && ( ap_add; exit 0; ) || ap_del
