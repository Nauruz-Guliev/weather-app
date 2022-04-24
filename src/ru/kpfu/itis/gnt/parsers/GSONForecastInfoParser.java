package ru.kpfu.itis.gnt.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.gnt.infoClasses.WeatherInfo;

public class GSONForecastInfoParser extends ForecastInfoParser{
    private WeatherInfo weatherInfo;

    @Override
    public void parse(String responseBody) {
        weatherInfo = new WeatherInfo();
        JsonArray rootElement = new JsonParser().parse(responseBody).getAsJsonArray();
        for (JsonElement jElement : rootElement) {
            JsonObject weatherObj = jElement.getAsJsonObject();
            weatherInfo.setTemperature(String.valueOf(weatherObj.getAsJsonObject("Temperature").getAsJsonObject("Metric").get("Value").getAsDouble()));
            weatherInfo.setWeatherDescription(weatherObj.get("WeatherText").getAsString());
            weatherInfo.setRelativeHumidity(String.valueOf(weatherObj.get("RelativeHumidity").getAsInt()));
            weatherInfo.setPrecipitationLvl(String.valueOf(weatherObj.getAsJsonObject("PrecipitationSummary").getAsJsonObject("Precipitation").getAsJsonObject("Metric").get("Value").getAsDouble()));
        }
    }
    @Override
    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }
}
