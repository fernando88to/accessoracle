package com.fernando.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsInRelativeOrder;

@QuarkusTest
class PessoaControllerTest {


    @Test
    public void testList() {
        given().when().get("/pessoa").then().statusCode(200).body("$.size()", is(2),
                "id", containsInRelativeOrder(2,1),
                "dataNascimento", containsInAnyOrder("03/05/2004","03/05/2003"),
                "nome", containsInAnyOrder("Henrique","Fernando")
        );
    }

}