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
import static payloadBuilder.WeatherPayload.sendMeasurementPayload;

import requestBuilder.WeatherRequestBuilder;




@Test
public class WeatherAPITests {
    static String ap;


    @Test (priority = 1)
    public void createWeatherStationTest() {
        WeatherRequestBuilder.registerStation()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");


}
@Test (priority = 2)
public static Response sendMeasurement(double temperature, double windSpeed, int humidity, String dateUTC) {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/measurements")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .body(sendMeasurementPayload(temperature, windSpeed, humidity, dateUTC))
                .post()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200 || status == 201) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/sendMeasurement.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
    @Test (priority = 3)
    public static Response getStations() {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/stations")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/getStations.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
    @Test (priority = 4)
    public static Response getStationID () {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/stations/{station_id}")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/getStationById.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
    @Test (priority = 5)
    public static Response updateStation(String external_id, String name, double latitude, double longitude, int altitude) {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/stations/{station_id}")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .body(registerStationPayload(external_id, name, latitude, longitude, altitude))
                .put()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/updateStation.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
    @Test (priority = 6)
    public static Response deleteStation() {
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/stations/{station_id}")
                .queryParam("appid", ap)
                .contentType(ContentType.JSON)
                .log().all()
                .delete()
                .then()
                .extract().response();
        int status = response.getStatusCode();
        if (status == 204) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/deleteStation.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }
        return response;
    }
}
