package com.snapp.testhotswapping.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "snapp.headers")
@Configuration("headerConf")
class HeaderConf {
    var all: Boolean? = null
    lateinit var names: List<String>
}