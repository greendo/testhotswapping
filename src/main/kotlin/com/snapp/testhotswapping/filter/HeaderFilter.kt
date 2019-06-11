package com.snapp.testhotswapping.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class HeaderFilter: Filter {

    @Autowired
    lateinit var inheritableThreadLocal: InheritableThreadLocal<Map<String, String>>

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, fc: FilterChain?) {
        println("hello filter")

        if (req is HttpServletRequest) {
            val headerNames = req.headerNames
            val headers = HashMap<String, String>()
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    val k = headerNames.nextElement()
                    try {
                        val v = req.getHeader(headerNames.nextElement())
                        headers[k] = v
                    } catch (e: Exception) {
                        println("header ${k} has no value")
                    }
                }
            }
            inheritableThreadLocal.set(headers)
        }

        println("goodbye filter")
        fc?.doFilter(req, res)
    }
}