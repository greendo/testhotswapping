package com.snapp.testhotswapping.filter

import com.snapp.testhotswapping.config.HeaderConf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

fun checkEl(headerConf: HeaderConf, name: String): Boolean {
    if (headerConf.all != null && headerConf.all == true) return true
    if (headerConf.names.contains(name)) return true
    return false
}

@Component
class HeaderFilterAttr: Filter {

    @Autowired
    lateinit var headerConf: HeaderConf

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, fc: FilterChain?) {
        println("hello attr filter")

        if (req is HttpServletRequest) {
            val headerNames = req.headerNames
            val headers = HashMap<String, String>()
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    val k = headerNames.nextElement()
                    if (!checkEl(headerConf, k)) continue
                    try {
                        val v = req.getHeader(headerNames.nextElement())
                        headers[k] = v
                    } catch (e: Exception) {
                        println("header ${k} has no value")
                    }
                }
            }
            req.setAttribute("attr", headers)
        }

        println("goodbye attr filter")
        fc?.doFilter(req, res)
    }
}

open class HeaderHolder {
    var content: Map<String, String>? = null
}

class HeaderFilterThreadLocal: Filter {

    @Autowired
    lateinit var headerConf: HeaderConf
    @Autowired
    lateinit var headerHolder: HeaderHolder

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, fc: FilterChain?) {
        println("hello thread filter")

        val map = HashMap<String, String>()
        if (req is HttpServletRequest) {
            val headerNames = req.headerNames
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    val k = headerNames.nextElement()
                    if (!checkEl(headerConf, k)) continue
                    try {
                        val v = req.getHeader(headerNames.nextElement())
                        map[k] = v
                    } catch (e: Exception) {
                        println("header ${k} has no value")
                    }
                }
            }
        }

        try {
            headerHolder.content = map
            println("goodbye thread filter")
            fc?.doFilter(req, res)
        } finally {
            headerHolder.content = null
        }
    }
}