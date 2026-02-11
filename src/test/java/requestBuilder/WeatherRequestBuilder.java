package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;

import static common.Authorization.wrongAPIkey;
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
                .body(registerStationPayload())
                .post()
                .then()
                .extract().response();

        stationId = response.jsonPath().getString("ID");

        return response;
    }


    public static Response createWeatherStationWithoutNameBodyRes() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(createWeatherStationWithoutNameBody())
                .post()
                .then()
                .extract().response();
    }

    public static Response createWeatherMeasurementResponse() {

        if (stationId == null) {
            throw new IllegalStateException("Weather station ID is null. Create station first.");
        }

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherMeasurementPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherMeasurementBody(stationId)) // JSONArray
                .post()
                .then()
                .extract().response();
    }

    public static Response createWeatherMeasurementWithoutDTResponse() {

        if (stationId == null) {
            throw new IllegalStateException("Weather station ID is null. Create station first.");
        }

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherMeasurementPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherMeasurementBodyWithoutDT(stationId)) // JSONArray
                .post()
                .then()
                .extract().response();
    }


    public static Response updateOpenWeatherResponse() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + stationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(updateWeatherStationBody())
                .put()
                .then()
                .extract().response();

    }



    public static Response updateOpenWeatherResponseWithoutExternalIdResponse() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + stationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(updateWeatherStationBodyWithoutExternalID())
                .put()
                .then()
                .extract().response();

    }

    public static Response getAllStationsOnOpenWeather() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();

    }

    public static Response getAllStationsOnOpenWeatherWithWrongAPIkey() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .queryParam("hhhh",wrongAPIkey)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();

    }

    public static Response getStationByIdOnOpenWeather() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + stationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();

    }

    public static Response deleteWeatherStation() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + stationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();

    }

}
