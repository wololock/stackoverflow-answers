package com.github.wololock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

final class JsonJava8Main {

    public static void main(String[] args) throws Exception {
        final Set<Locale> locales = new HashSet<>(Arrays.asList(Locale.ENGLISH, Locale.GERMAN, Locale.CHINA));
        final String oldApproach = languageString(locales);
        final String newApproach = languageStringUsingStream(locales);

        System.out.println(oldApproach);
        System.out.println(newApproach);

        assert oldApproach.equals(newApproach);
    }

    public static String languageStringUsingStream(Set<Locale> locales) {
        return new JSONObject()
                .put("root", locales.stream()
                        .map(locale -> new JSONObject()
                                .put("lcode", locale.toLanguageTag())
                                .put("ldisplay", locale.getDisplayName(Locale.ENGLISH))
                        )
                        .reduce(new JSONArray(), JSONArray::put, (a, b) -> a))
                .toString();
    }

    // Old function to replace with Java Stream API
    public static String languageString(Set<Locale> languageSet) throws Exception {
        JSONObject json = new JSONObject();
        JSONObject tempj = new JSONObject();
        JSONArray jArr = new JSONArray();
        try {
            for (Locale locale : languageSet) {
                if (locale != null) {
                    tempj = new JSONObject();
                    tempj.put("lcode", locale.toLanguageTag());
                    tempj.put("ldisplay", locale.getDisplayName(Locale.ENGLISH));
                    jArr.put(tempj);
                }
            }
            json.put("root", jArr);
        } catch (JSONException e) {
            //
        }
        return json.toString();
    }
}
