package ru.kpfu.itis.gnt.infoClasses;

public class WeatherInfo {
    private String temperature;
    private String weatherDescription;
    private String relativeHumidity;
    private String precipitationLvl;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getPrecipitationLvl() {
        return precipitationLvl;
    }

    public void setPrecipitationLvl(String precipitationLvl) {
        this.precipitationLvl = precipitationLvl;
    }
}
