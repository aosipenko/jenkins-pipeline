package org.prog.testng.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
public class RestTests {

    @Test
    public void randomUserTest() {
        System.out.println("Bill Account ID" + System.getProperty("type", "NONE"));
        RequestSpecification specification = RestAssured.given();
        specification.baseUri("https://randomuser.me/");
        specification.basePath("/api/");
        specification.queryParam("inc", "gender,name,nat");
        specification.queryParam("noinfo");
        specification.queryParam("results", 10);

        Response response = specification.get();
        ValidatableResponse validatableResponse = response.then();

        validatableResponse.statusCode(200);
        validatableResponse.contentType(ContentType.JSON);
        response.body().prettyPrint();
        validatableResponse.body("results.findAll { it.gender == 'female' }.size()", greaterThan(0));
        List<String> genders = response.jsonPath().getList("results.gender");

        for (String g : genders) {
            System.out.println(g);
        }
    }
}
