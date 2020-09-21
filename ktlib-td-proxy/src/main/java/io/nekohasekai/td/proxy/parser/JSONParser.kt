package io.nekohasekai.td.proxy.parser

import cn.hutool.json.JSON
import cn.hutool.json.JSONArray
import cn.hutool.json.JSONObject
import io.nekohasekai.td.proxy.impl.Proxy

object JSONParser : Parser<JSON> {

    override fun parseProxies(value: JSON): List<Proxy> = mutableListOf<Proxy>().apply parse@{

        if (value is JSONArray) {

            value.forEach {

                addAll(Parser.parseProxies(it))

            }

        } else if (value is JSONObject) {

            var noSubEntity = true

            value.forEach { (_, value) ->

                when (value) {

                    is JSONArray -> {

                        addAll(parseProxies(value))

                        noSubEntity = false

                    }

                    is JSONObject -> {

                        addAll(parseProxies(value))

                        noSubEntity = false

                    }

                    is String -> {

                        addAll(StringParser.parseProxies(value).also {

                            if (it.isNotEmpty()) {

                                addAll(it)

                                noSubEntity = false

                            }

                        })

                    }
                }


            }

            if (!noSubEntity) return@parse

            addAll(MapParser.parseMap(value))

        }

    }

}