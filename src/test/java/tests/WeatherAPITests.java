package tests;

import org.testng.annotations.Test;
import requestBuilder.WeatherRequestBuilder;

@Test
public class WeatherAPITests {


    @Test(priority = 1)
    public void createWeatherStationTest() {
        WeatherRequestBuilder.registerStation()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");

    }

    @Test(priority = 2)
    public void createWeatherStationWithoutNameNegativeTest() {
        WeatherRequestBuilder.createWeatherStationWithoutNameBodyRes()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(400)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 3)
    public void createWeatherStationMeasurementTest(){
        WeatherRequestBuilder.createWeatherMeasurementResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204);
    }

    @Test(priority = 3)
    public void createWeatherStationMeasurementWithoutDTNegativeTest(){
        WeatherRequestBuilder.createWeatherMeasurementWithoutDTResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(400)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 5 )
    public void getWeatherStationTest() {
        WeatherRequestBuilder.getAllStationsOnOpenWeather()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 6)
    public void getWeatherStationNegativeTest() {
        WeatherRequestBuilder.getAllStationsOnOpenWeatherWithWrongAPIkey()
                .then()
                .log().all()
                .assertThat()
                .statusCode(401)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 7)
    public void getWeatherStationByIdTest() {
        WeatherRequestBuilder.getStationByIdOnOpenWeather()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 8)
    public void updateWeatherStationTest(){
        WeatherRequestBuilder.updateOpenWeatherResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 9)
    public void updateWeatherStationWithoutExternalIdNegativeTest(){
        WeatherRequestBuilder.updateOpenWeatherResponseWithoutExternalIdResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 10)
    public void deleteWeatherStationTest(){
        WeatherRequestBuilder.deleteWeatherStation()
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);
    }


}