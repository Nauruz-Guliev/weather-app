package ru.kpfu.itis.gnt.parsers;

import ru.kpfu.itis.gnt.infoClasses.CitiesInfo;

public abstract class CityInfoParser {
    public abstract void parse(String responseBody);
    void initCities(CitiesInfo[] citiesInfo) {
        for(int i = 0; i<citiesInfo.length; i++) {
            citiesInfo[i] = new CitiesInfo();
        }
    }
    public abstract CitiesInfo[] getCities();
}
