package com.practice.catalogservice;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ProductControllerIT extends AbstractIT {

    @Test
    void shouldReturnProducts() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10)) // Assuming your PagedResult wraps the list in 'data' or 'content'
                .body("totalElements", is(40))
                .body("pageNumber", is(1));
    }

    @Test
    void shouldReturnProductByCode() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/BK-001")
                .then()
                .statusCode(200)
                .body("code", is("BK-001"))
                .body("name", is("Clean Code"))
                .body("price", is(45.50f));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeUnknown() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/INVALID-CODE")
                .then()
                .statusCode(404)
                .body("message", containsString("was not found"))
                .body("status", is(404));
    }
}
