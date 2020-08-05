package com.flamexander.demo.rest;

import com.flamexander.demo.rest.entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ItemControllerTests extends BaseTests {

    @Test
    public void updateExistsItemTest() {
        String title = "New Title " + new Date();

        Item item = new Item();
        item.setTitle(title);
        item.setId(1L);

        Item responseItem = given()
                .spec(specification)
                .body(item)
                .when()
                .put("/items")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().as(Item.class);

        Assertions.assertEquals(title, responseItem.getTitle());
        Assertions.assertEquals(1L, responseItem.getId());
    }

    @Test
    public void updateNullIdItemTest() {
        Item item = new Item();
        item.setTitle("Null item");

        given()
                .spec(specification)
                .body(item)
                .when()
                .put("/items")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Item doesn't exists"));
    }

    @Test
    public void updateNotExistsItem() {
        Item item = new Item();
        item.setTitle("Not Exists item");
        item.setId(4L);

        given()
                .spec(specification)
                .body(item)
                .when()
                .put("/items")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Item doesn't exists"));
    }
}