package com.snapp.testhotswapping.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @Autowired
    lateinit var inheritableThreadLocal: InheritableThreadLocal<Map<String, String>>

    @GetMapping(value = "/a")
    fun a(): String {
        println("hello controller")
        println(inheritableThreadLocal.get())
        println("goodbye controller")
        return "a"
    }

    @GetMapping(value = "/b")
    fun b(): String = "b"
}