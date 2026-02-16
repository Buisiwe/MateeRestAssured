package tests;

import org.testng.annotations.Test;
import requestBuilder.NdosiAPIRequestBuilder;

public class NdosiAPITests {

    @Test(priority = 0)
    public void registerUserTest() {
        NdosiAPIRequestBuilder.registerUser()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 1)
    public void loginUserTest() {
        NdosiAPIRequestBuilder.loginUser()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

    }

    @Test(priority = 2)
    public void getUserProfileTest() {
        NdosiAPIRequestBuilder.getUserProfile()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 3)
    public void updateUserProfileTest() {
        NdosiAPIRequestBuilder.updateUserProfile()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 4)
    public void createTestimonialTest() {
        NdosiAPIRequestBuilder.createTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 5)
    public void updateTestimonialTest() {
        NdosiAPIRequestBuilder.updateTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 6)
    public void retrieveTestimonialTest() {
        NdosiAPIRequestBuilder.getTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 7)
    void deleteTestimonialTest() {
        NdosiAPIRequestBuilder.deleteTestimonial()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
