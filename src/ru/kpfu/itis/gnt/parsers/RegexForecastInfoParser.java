package ru.kpfu.itis.gnt.parsers;

import ru.kpfu.itis.gnt.infoClasses.WeatherInfo;
import ru.kpfu.itis.gnt.util.RegexOccurrences;

public class RegexForecastInfoParser extends ForecastInfoParser{

    private WeatherInfo weatherInfo;
    private final static String TEMPERATURE_REGEX = "(Temperature[\"\\w:{}]+V[\"\\w]+):([0-9\\.]+)";
    private final static String WEATHER_TEXT_REGEX = "(\"W[\\w]+t\"):(\"[\\w]+\")";
    private final static String RELATIVE_HUMIDITY_REGEX = "(\"R[\\w]+y\"):([\\d]+)";
    private final static String PRECIPITATION_REGEX = "(PrecipitationSummary[:{\"\\w]+V[\\w]+\"):([0-9\\.]+)";

    @Override
    public void parse(String responseBody) {
        weatherInfo = new WeatherInfo();
        weatherInfo.setTemperature(RegexOccurrences.getMap(TEMPERATURE_REGEX,responseBody).get(0));
        weatherInfo.setWeatherDescription(RegexOccurrences.getMap(WEATHER_TEXT_REGEX,responseBody).get(0));
        weatherInfo.setRelativeHumidity(RegexOccurrences.getMap(RELATIVE_HUMIDITY_REGEX,responseBody).get(0));
        weatherInfo.setPrecipitationLvl(RegexOccurrences.getMap(PRECIPITATION_REGEX,responseBody).get(0));
    }

    @Override
    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }
}
