package ru.kpfu.itis.gnt.util;

import java.net.MalformedURLException;
import java.net.URL;

public class WeatherURLS {
    private URL urlCities;
    private URL urlForecast;
    private final String API_KEY;
    private int cityId;

    public WeatherURLS(String API_KEY) throws MalformedURLException {
        this.API_KEY = API_KEY;
    }

    public URL getUrlCities() {
        return urlCities;
    }

    public void setUrlCities(String city) throws MalformedURLException {
        this.urlCities = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + API_KEY + "&q=" + city);
    }

    public URL getUrlForecast() {
        return urlForecast;
    }

    public void setUrlForecast(int cityKey) throws MalformedURLException {
        this.urlForecast = new URL("http://dataservice.accuweather.com/currentconditions/v1/" + cityKey + "?apikey=" + API_KEY + "&details=true");
    }
}
