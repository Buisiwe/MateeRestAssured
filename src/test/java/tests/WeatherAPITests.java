package tests;

import org.testng.annotations.Test;
import requestBuilder.WeatherRequestBuilder;

@Test
public class WeatherAPITests {

    static String ap;

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

    @Test(priority = 4 )
    public void getWeatherStationTest() {
        WeatherRequestBuilder.getAllStationsOnOpenWeather()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 5)
    public void getWeatherStationByIdTest() {
        WeatherRequestBuilder.getStationByIdOnOpenWeather()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @Test(priority = 6)
    public void updateWeatherStationTest(){
        WeatherRequestBuilder.updateOpenWeatherResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 7)
    public void deleteWeatherStationTest(){
        WeatherRequestBuilder.deleteWeatherStation()
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);
    }


}