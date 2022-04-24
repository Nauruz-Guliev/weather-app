package ru.kpfu.itis.gnt.parsers;

import ru.kpfu.itis.gnt.infoClasses.CitiesInfo;
import ru.kpfu.itis.gnt.util.RegexOccurrences;

public class RegexCitiesInfoParser extends CityInfoParser{
    private final static String COUNTRY_ID_REGEX = "(\"[\"\\w]+ID\"):([\"A-Z]+)";
    private final static String LOCALIZED_TYPE_REGEX = "(\"L[\\w]+Type\"):([\"a-zA-Z]+)";
    private final static String LOCALIZED_NAME_REGEX = "(\"L[\\w]+Name\"):([\"a-zA-Z]+)";
    private final static String KEY_REGEX = "(\"Key\"):\"([0-9]+)";
    private CitiesInfo[] citiesInfo;



    @Override
    public void parse(String responseBody) {
        citiesInfo = new CitiesInfo[RegexOccurrences.getMap(KEY_REGEX,responseBody).size()];
        initCities(citiesInfo);
        for (int i = 0; i < citiesInfo.length; i++) {
            citiesInfo[i].setCountryID(RegexOccurrences.getMap(COUNTRY_ID_REGEX, responseBody).get(i));
            citiesInfo[i].setLocalizedType(RegexOccurrences.getMap(LOCALIZED_TYPE_REGEX, responseBody).get(i));
            citiesInfo[i].setLocalizedName(RegexOccurrences.getMap(LOCALIZED_NAME_REGEX, responseBody).get(i));
            citiesInfo[i].setKey(RegexOccurrences.getMap(KEY_REGEX, responseBody).get(i));
        }
    }
    @Override
    public CitiesInfo[] getCities() {
        return citiesInfo;
    }
}
