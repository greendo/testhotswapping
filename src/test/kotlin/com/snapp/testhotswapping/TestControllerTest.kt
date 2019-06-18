package com.snapp.testhotswapping

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.Header
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.web.server.LocalServerPort
//import io.restassured.module.mockmvc.RestAssuredMockMvc.*



@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

    @LocalServerPort
    lateinit var port: Integer

    @Test
    fun testThreadFilteHasDifferentIdInDifferentRequests() {
        val vars = listOf(1, 2, 3)

        RestAssured.port = port.toInt()

        vars.forEach {
            given()
//                    .standaloneSetup(TestController())
                    .header("TestHeader${it}", "someData")
                    .`when`()
                    .get("/a")
                    .then()
                    .statusCode(200)
        }
    }
}