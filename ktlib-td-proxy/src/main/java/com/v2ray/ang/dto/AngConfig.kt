package com.v2ray.ang.dto

import cn.hutool.core.codec.Base64
import com.google.gson.Gson
import com.v2ray.ang.V2RayConfig
import com.v2ray.ang.V2RayConfig.SS_PROTOCOL
import com.v2ray.ang.V2RayConfig.VMESS_PROTOCOL

data class AngConfig(
        var index: Int,
        var vmess: ArrayList<VmessBean>,
        var subItem: ArrayList<SubItemBean>
) {
    data class VmessBean(var guid: String = "123456",
                         var address: String = "",
                         var port: Int = 443,
                         var id: String = "",
                         var alterId: Int = 64,
                         var security: String = "auto",
                         var network: String = "tcp",
                         var remarks: String = "",
                         var headerType: String = "none",
                         var requestHost: String = "",
                         var path: String = "",
                         var streamSecurity: String = "",
                         var configType: Int = 1,
                         var configVersion: Int = 2,
                         var testResult: String = "") {

        override fun equals(other: Any?): Boolean {
            return super.equals(other) || (other is VmessBean &&
                    address == other.address &&
                    port == other.port &&
                    id == other.id &&
                    network == other.network &&
                    headerType == other.headerType &&
                    requestHost == other.requestHost &&
                    path == other.path)
        }

        override fun toString(): String {

            when (configType) {

                V2RayConfig.EConfigType.Vmess -> {

                    val vmessQRCode = VmessQRCode()

                    vmessQRCode.v = configVersion.toString()
                    vmessQRCode.ps = remarks
                    vmessQRCode.add = address
                    vmessQRCode.port = port.toString()
                    vmessQRCode.id = id
                    vmessQRCode.aid = alterId.toString()
                    vmessQRCode.net = network
                    vmessQRCode.type = headerType
                    vmessQRCode.host = requestHost
                    vmessQRCode.path = path
                    vmessQRCode.tls = streamSecurity

                    return VMESS_PROTOCOL + Base64.encode(Gson().toJson(vmessQRCode))

                }

                V2RayConfig.EConfigType.Shadowsocks -> {

                    val remark = "#" + Base64.encodeUrlSafe(remarks)

                    val url = String.format("%s:%s@%s:%s", security, id, address, port)

                    return SS_PROTOCOL + Base64.encode(url.toByteArray(charset("UTF-8"))) + remark

                }

                else -> error("invalid vmess bean type")

            }

        }

        override fun hashCode(): Int {
            var result = guid.hashCode()
            result = 31 * result + address.hashCode()
            result = 31 * result + port
            result = 31 * result + id.hashCode()
            result = 31 * result + security.hashCode()
            result = 31 * result + network.hashCode()
            result = 31 * result + headerType.hashCode()
            result = 31 * result + requestHost.hashCode()
            result = 31 * result + path.hashCode()
            result = 31 * result + streamSecurity.hashCode()
            return result
        }

    }

    data class SubItemBean(var id: String = "",
                           var remarks: String = "",
                           var url: String = "")
}
