package com.snapp.testhotswapping.controller

import com.snapp.testhotswapping.filter.HeaderHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class TestController {

    @Autowired
    lateinit var headerHolder: HeaderHolder

    @GetMapping(value = "/a")
    fun a(req: HttpServletRequest, res: HttpServletResponse): String {
        println("hello controller")
        println(req.headerNames.toList())
        println("reqAttr: " + req.getAttribute("attr"))
        println("threadLocal: " + headerHolder.content)
        println("goodbye controller")
        return "a"
    }

    @GetMapping(value = "/b")
    fun b(): String = "b"
}