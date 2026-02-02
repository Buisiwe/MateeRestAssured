package payloadBuilder;
import org.json.simple.JSONObject;

public class WeatherPayload {
    public static JSONObject registerStationPayload(String external_id, String name, double latitude, double longitude, int altitude) {

        JSONObject registerStation = new JSONObject();
        registerStation.put("external_id", external_id);
        registerStation.put("name", name);
        registerStation.put("latitude", latitude);
        registerStation.put("longitude", longitude);
        registerStation.put("altitude", altitude);

        return registerStation;
    }

    public static JSONObject sendMeasurementPayload(double temperature, double windSpeed, int humidity, String dateUTC) {

        JSONObject sendMeasurement = new JSONObject();
        sendMeasurement.put("temperature", temperature);
        sendMeasurement.put("windSpeed", windSpeed);
       //sendMeasurement.put("windGust", windGust;

        sendMeasurement.put("humidity", humidity);
        sendMeasurement.put("date_utc", dateUTC);

        return sendMeasurement;
    }

    public static JSONObject getStationsPayload() {

        JSONObject getStations = new JSONObject();

        return getStations;
    }

    public static JSONObject getStationByIdPayload() {

        JSONObject getStationById = new JSONObject();

        return getStationById;
    }
    public static JSONObject updateStationPayload(String external_id, String name, double latitude, double longitude, int altitude) {

        JSONObject updateStation = new JSONObject();
        updateStation.put("external_id", external_id);
        updateStation.put("name", name);
        updateStation.put("latitude", latitude);
        updateStation.put("longitude", longitude);
        updateStation.put("altitude", altitude);

        return updateStation;
    }
    public static JSONObject deleteStationPayload() {

        JSONObject deleteStation = new JSONObject();

        return deleteStation;
    }

}

