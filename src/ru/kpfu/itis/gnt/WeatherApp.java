package ru.kpfu.itis.gnt;

import ru.kpfu.itis.gnt.infoClasses.CitiesInfo;
import ru.kpfu.itis.gnt.infoClasses.WeatherInfo;
import ru.kpfu.itis.gnt.parsers.*;
import ru.kpfu.itis.gnt.util.UrlResponse;
import ru.kpfu.itis.gnt.util.WeatherURLS;

import java.net.MalformedURLException;
import java.util.Scanner;

public class WeatherApp extends Application {
    public static final String ANSI_LIGHT_BLUE = "\u001B[36m";
    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private Scanner sc;
    private WeatherURLS urls;
    private String API_KEY;
    private CityInfoParser cityInfoParser;
    private ForecastInfoParser forecastInfoParser;
    private CitiesInfo[] cities;

    @Override
    public void init() throws MalformedURLException {
        API_KEY = "YQnfN5GJFWTLrJsfHyzdNdZ9jzfFFPEB";
        urls = new WeatherURLS(API_KEY);
        sc = new Scanner(System.in);
    }

    @Override
    public void start() {
        System.out.println("*************WEATHER APP*************\n" + "Type in exit to close the app at any time. \n");
        System.out.print("(is GSON by default) Type in R for regex or G for GSON >");
        initAppropriateParsers(checkInputIfExit(sc.nextLine()));
        while (true) {
            try {
                System.out.print("Type in the name of your city > ");
                String city = checkInputIfExit(sc.nextLine());
                urls.setUrlCities(city); //adding city name to url
                cityInfoParser.parse(UrlResponse.getHttpResponse(urls.getUrlCities())); // getting json response from server
                if (cityInfoParser.getCities().length == 0) { // if no city found than throw exception
                    throw new IllegalArgumentException("Such a city does not exist.");
                } else {
                    cities = cityInfoParser.getCities();
                }
                for (int i = 0; i < cities.length; i++) { // print all found cities with given name
                    System.out.println(ANSI_LIGHT_BLUE + "ID: " + i + "\t\t CountryID: " + cities[i].getCountryID() + " Type: " + cities[i].getLocalizedType() + " RegionName: " + cities[i].getLocalizedName() + ANSI_RESET);
                }
                System.out.print("Choose the appropriate city from the list above and type in its ID > ");
                String cityId = checkInputIfExit(sc.nextLine()); // choosing appropriate city
                if (Integer.parseInt(cityId) < 0 || Integer.parseInt(cityId) > cities.length) {
                    throw new IllegalArgumentException("There is no city with such an index");
                }
                urls.setUrlForecast(Integer.parseInt(cities[Integer.parseInt(cityId)].getKey())); //creating url for the chosen city
                forecastInfoParser.parse(UrlResponse.getHttpResponse(urls.getUrlForecast())); // getting json response
                WeatherInfo weatherInfo = forecastInfoParser.getWeatherInfo();//getting info about the city
                System.out.println(ANSI_PURPLE + "Weather in " + city + " now " + ANSI_YELLOW + "\n Temperature: " + weatherInfo.getTemperature() + "°C" + "\t Description: " + weatherInfo.getWeatherDescription() + "\t Humidity: " + weatherInfo.getRelativeHumidity() + "\tPrecipitation: " + weatherInfo.getPrecipitationLvl() + "mm" + ANSI_RESET);
            } catch (MalformedURLException | IllegalArgumentException ex) {
                System.out.println(ANSI_RED + ex.getMessage() + ANSI_RESET);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println(ANSI_RED + "Wrong ID is given: " + ex.getMessage() + ANSI_RESET);
            }
        }
    }

    private void initAppropriateParsers(String userChoice) {
        if (userChoice.equals("R")) {
            cityInfoParser = new RegexCitiesInfoParser();
            forecastInfoParser = new RegexForecastInfoParser();
        } else {
            cityInfoParser = new GSONCitiesInfoParser();
            forecastInfoParser = new GSONForecastInfoParser();
        }
    }

    private String checkInputIfExit(String input) {
        if (input.equals("exit")) {
            System.exit(0);
        }
        return input;
    }
}
