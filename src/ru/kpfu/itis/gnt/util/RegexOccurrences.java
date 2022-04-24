package ru.kpfu.itis.gnt.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOccurrences {
    public static ArrayList<String> getMap(String patternToSearch, String responseBody) {
        Pattern pattern = Pattern.compile(patternToSearch);
        Matcher matcher = pattern.matcher(responseBody);
        ArrayList<String> valuesFound = new ArrayList<>();
        while (matcher.find()) {
            valuesFound.add(matcher.group(2));
        }
        return valuesFound;
    }
}
