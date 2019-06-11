package com.snapp.testhotswapping.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class Config {
    @Bean
    @Scope("prototype")
    fun getInheritableThreadLocal(): InheritableThreadLocal<Map<String, String>>{
        return InheritableThreadLocal()
    }
}