package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;

import static common.BaseWeatherURI.baseWeatherURL;
import static common.BasePaths.*;
import static payloadBuilder.WeatherPayload.*;
import static common.Authorization.openWeatherApiKey;


public class WeatherRequestBuilder {

    static String stationId;

    public static Response registerStation() {
        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(registerStationPayload("TestExternalID",
                        "TestStation",
                        40.7128,
                        -74.0060,
                        10))
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
    }
public static Response sendMeasurement(double temperature, double windSpeed, int humidity, String dateUTC) {
        Response response = RestAssured.given()
                .baseUri(baseWeatherURL)
                .basePath("/measurements")
                .queryParam("appid", openWeatherApiKey)
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
    public static Response getStations() {
        Response response = RestAssured.given()
                .baseUri(baseWeatherURL)
                .basePath("/stations")
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(getStationsPayload())
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
    public static Response getStationID () {
        Response response = RestAssured.given()
                .baseUri(baseWeatherURL)
                .basePath("/stations/" + stationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(getStationByIdPayload())
                .get()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/getStationID.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
    public static Response updateStation () {
        Response response = RestAssured.given()
                .baseUri(baseWeatherURL)
                .basePath("/stations/" + stationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(updateStationPayload("updated_external_id", "Updated Station Name", 45.0, -75.0, 200))
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
    public static Response deleteStation () {
        Response response = RestAssured.given()
                .baseUri(baseWeatherURL)
                .basePath("/stations/" + stationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(deleteStationPayload())
                .delete()
                .then()
                .extract().response();

        int status = response.getStatusCode();
        if (status == 200 || status == 204) {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/deleteStation.json")));
        } else {
            Assert.fail("API error: HTTP " + status + " - " + response.asString());
        }

        return response;
    }
}
