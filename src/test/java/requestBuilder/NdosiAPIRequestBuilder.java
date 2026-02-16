package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static common.BasePaths.*;


import static payloadBuilder.NdosiAPIPayload.*;

public class NdosiAPIRequestBuilder {
    static String userToken;
    static String testimonialId;

    public static Response registerUser() {
                return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(registerPath)
                .contentType(ContentType.JSON)
                .log().all()
                .body(registerUserPayload().toJSONString())
                .post()
                .then()
                .extract().response();
    }

    public static Response loginUser() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(loginPath)
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginUserPayload().toJSONString())
                .post()
                .then()
                .extract().response();

        userToken = response.jsonPath().getString("data.token");

        return response;
    }
    public static Response createTestimonial( ) {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(testimonialsPath)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .body(createTestimonialPayload())
                .post()
                .then()
                .extract().response();

            testimonialId = response.jsonPath().getString("data.Id");
        return response;
    }

    public static Response getUserProfile() {
        return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(userProfilePath)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response updateUserProfile() {

        return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(userProfilePath)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .body(updateUserProfilePayload())
                .put()
                .then()
                .extract().response();
    }

    public static Response getTestimonial() {
        return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(testimonialsPath)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response updateTestimonial() {
       return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(testimonialsPath + "/" + testimonialId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .body(updateTestimonialPayload())
                .put()
                .then()
                .extract().response();
    }

    public static Response deleteTestimonial() {
        return RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath(testimonialsPath + "/" + testimonialId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
