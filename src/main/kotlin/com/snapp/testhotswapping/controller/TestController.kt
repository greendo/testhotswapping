package com.snapp.testhotswapping.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping(value = "/a")
    fun a(): String = "a"

    @GetMapping(value = "/b")
    fun b(): String = "b"
}