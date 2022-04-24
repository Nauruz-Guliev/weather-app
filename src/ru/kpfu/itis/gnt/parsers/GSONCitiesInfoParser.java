package ru.kpfu.itis.gnt.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.gnt.infoClasses.CitiesInfo;

public class GSONCitiesInfoParser extends CityInfoParser {
    private CitiesInfo[] citiesInfo;
    @Override
    public void parse(String responseBody) {
        JsonArray rootElement = new JsonParser().parse(responseBody).getAsJsonArray();
        citiesInfo = new CitiesInfo[rootElement.size()];
        initCities(citiesInfo);
        for (int i = 0; i< rootElement.size(); i++) {
            JsonObject city = rootElement.get(i).getAsJsonObject();
            citiesInfo[i].setCountryID(city.getAsJsonObject("AdministrativeArea").get("CountryID").getAsString());
            citiesInfo[i].setLocalizedType(city.getAsJsonObject("AdministrativeArea").get("LocalizedType").getAsString());
            citiesInfo[i].setLocalizedName(city.getAsJsonObject("AdministrativeArea").get("LocalizedName").getAsString());
            citiesInfo[i].setKey(city.get("Key").getAsString());
        }
    }

    @Override
    public CitiesInfo[] getCities() {
        return citiesInfo;
    }

}
