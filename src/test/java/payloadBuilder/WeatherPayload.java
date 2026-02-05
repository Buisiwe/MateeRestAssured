package payloadBuilder;
import org.json.simple.JSONObject;

public class WeatherPayload {
    public static JSONObject registerStationPayload() {

        JSONObject registerStation = new JSONObject();
        registerStation.put("external_id", "SF_TEST001");
        registerStation.put("name", "San Francisco Test Station");
        registerStation.put("latitude", 37.76);
        registerStation.put("longitude", -122.43);
        registerStation.put("altitude", 150);

        return registerStation;
    }

    public static JSONObject createWeatherStationWithoutNameBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }

    public static JSONObject sendMeasurementPayload() {

        JSONObject sendMeasurement = new JSONObject();
        sendMeasurement.put("station_id", "6983ab09cbd4230001cc3515");
        sendMeasurement.put("dt", 1479817340);
        sendMeasurement.put("temperature", 18.7);
        sendMeasurement.put("windSpeed", 1.2);
        sendMeasurement.put("windGust", 3.4);
        sendMeasurement.put("pressure", 1021);
        sendMeasurement.put("humidity", 87);
        sendMeasurement.put("rain_1h", 2);
        sendMeasurement.put("clouds",(sendMeasurement.put("conditions","NSC")));

        return sendMeasurement;
    }



    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",40.23);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }



}

