package com.snapp.testhotswapping

import com.snapp.testhotswapping.controller.TestController
import io.restassured.http.Header
import org.junit.Test
import io.restassured.module.mockmvc.RestAssuredMockMvc.*

class TestControllerTest {

    @Test
    fun testThreadFilteHasDifferentIdInDifferentRequests() {
        val vars = listOf(1, 2, 3)

        vars.forEach {
            given()
                    .standaloneSetup(TestController())
                    .header(Header("TestHeader${it}", "someData"))
                    .param("name", "Johan")
                    .`when`()
                    .get("/a")
                    .then()
                    .statusCode(200)
        }
    }
}