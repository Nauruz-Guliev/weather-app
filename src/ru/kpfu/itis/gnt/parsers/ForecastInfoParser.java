package ru.kpfu.itis.gnt.parsers;

import ru.kpfu.itis.gnt.infoClasses.WeatherInfo;

public abstract class ForecastInfoParser {
    public abstract void parse(String responseBody);
    public abstract WeatherInfo getWeatherInfo();

}
