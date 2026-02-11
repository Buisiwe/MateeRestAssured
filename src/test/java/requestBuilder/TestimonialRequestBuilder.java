package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import static common.BasePaths.*;


import static payloadBuilder.TestimonialsPayload.*;

public class TestimonialRequestBuilder {

    static String  testimonialToken;
    public static Response registerUser() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/register")
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginUserPayload().toJSONString())
                .post()
                .then()
                .extract().response();

        return response;
    }

    public static Response loginUser() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginUserPayload().toJSONString())
                .post()
                .then()
                .extract().response();

        testimonialToken = response.jsonPath().getString("token");

        return response;
    }
    public static Response createTestimonial( ) {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/testimonials")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .body(createTestimonialPayload())
                .post()
                .then()
                .extract().response();
        return response;
    }

    public static Response getUserProfile() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/profile")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .get()
                .then()
                .extract().response();
        return response;
    }

    public static Response updateUserProfile() {

        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/profile")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .body(updateUserProfilePayload("token","email").toJSONString())
                .put()
                .then()
                .extract().response();
        return response;
    }

    public static Response getTestimonial() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/testimonials")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .get()
                .then()
                .extract().response();
        return response;
    }

    public static Response updateTestimonial() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/testimonials")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .body(updateTestimonialPayload("user_Id"))
                .put()
                .then()
                .extract().response();
        return response;
    }

    public static Response deleteTestimonial() {
        Response response = RestAssured.given()
                .baseUri(testimonialBaseUrl)
                .basePath("/APIDEV/testimonials")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + testimonialToken)
                .log().all()
                .delete()
                .then()
                .extract().response();
        return response;
    }
}
