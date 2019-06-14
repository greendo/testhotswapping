package com.snapp.testhotswapping.config

import com.snapp.testhotswapping.filter.HeaderFilterThreadLocal
import com.snapp.testhotswapping.filter.HeaderHolder
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.aop.target.ThreadLocalTargetSource
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Primary



@Configuration
class Config {

    @Bean(name = ["headerHolder"])
    @Scope("prototype")
    fun getHeaderHolder(): HeaderHolder = HeaderHolder()

    @Bean(destroyMethod = "destroy")
    fun getThreadLocalHeaderMap(): ThreadLocalTargetSource {
        val result = ThreadLocalTargetSource()
        result.targetBeanName = "headerHolder"
        return result
    }

    @Primary
    @Bean(name = ["proxiedThreadLocalTargetSource"])
    fun proxiedThreadLocalTargetSource(threadLocalTargetSource: ThreadLocalTargetSource): ProxyFactoryBean {
        val result = ProxyFactoryBean()
        result.targetSource = threadLocalTargetSource
        return result
    }

    @Bean
    fun getHeaderFilterTL(): HeaderFilterThreadLocal = HeaderFilterThreadLocal()

    @Bean
    fun headerFilterTLRegistration(): FilterRegistrationBean<*> {
        val result = FilterRegistrationBean<HeaderFilterThreadLocal>()
        result.filter = this.getHeaderFilterTL()
        val url = ArrayList<String>()
        url.add("/*")
        result.urlPatterns = url
        result.setName("TL Store Filter")
        result.order = 1
        return result
    }
}