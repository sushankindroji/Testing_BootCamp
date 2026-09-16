package com.commonfloor.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "GET /users — verify 200 and 10 users")
    public void testGetAllUsers() {
        Response response =
            given()
            .when()
                .get("/users")
            .then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .extract().response();

        System.out.println("GET /users — Status: " + response.getStatusCode()
            + " | Users: " + response.jsonPath().getList("$").size());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "GET /posts/1 — verify single post")
    public void testGetSinglePost() {
        Response response =
            given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyString()))
                .extract().response();

        System.out.println("GET /posts/1 — Status: " + response.getStatusCode()
            + " | Title: " + response.jsonPath().getString("title"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "POST /posts — verify 201 created")
    public void testCreatePost() {
        String body = "{ \"title\": \"REST Assured Demo\", \"body\": \"CommonFloor API Test\", \"userId\": 1 }";

        Response response =
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().response();

        System.out.println("POST /posts — Status: " + response.getStatusCode()
            + " | Created ID: " + response.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
