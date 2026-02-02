package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static common.BaseURI.baseURL;
import static payloadBuilder.TestimonialsPayload.loginUserPayload;
import static payloadBuilder.WeatherPayload.registerStationPayload;

public class WeatherAPITests {
    static String ap;


    @Test
    public static Response registerStation(String external_id, String name, double latitude, double longitude, int altitude) {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/stations")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .body(registerStationPayload(external_id, name, latitude, longitude, altitude))
                .post()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200 || status == 201) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/registerStation.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
}}
