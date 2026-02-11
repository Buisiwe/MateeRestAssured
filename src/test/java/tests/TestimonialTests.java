package tests;

import org.testng.annotations.Test;
import requestBuilder.TestimonialRequestBuilder;

public class TestimonialTests {

    @Test(priority = 0)
    public void registerUserTest() {
        TestimonialRequestBuilder.registerUser()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 1)
    public void loginUserTest() {
        TestimonialRequestBuilder.loginUser()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

    }

     @Test(priority = 2)
    public void getUserProfileTest() {
        TestimonialRequestBuilder.getUserProfile()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 3)
    public void updateUserProfileTest() {
        TestimonialRequestBuilder.updateUserProfile()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 4)
    public void createTestimonialTest() {
        TestimonialRequestBuilder.createTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 5)
    public void updateTestimonialTest() {
        TestimonialRequestBuilder.updateTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 6)
    public void retrieveTestimonialTest() {
        TestimonialRequestBuilder.getTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test void deleteTestimonialTest() {
        TestimonialRequestBuilder.deleteTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204);
    }
}
