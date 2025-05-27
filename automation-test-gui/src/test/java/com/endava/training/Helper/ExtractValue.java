package com.endava.training.Helper;

public class ExtractValue {
    public String extractNumericValue(String input) {
        return input.replaceAll("[^\\d.]", "");
    }
    public String extractBaseUrl(String url) {
        if (url == null) return null;
        int index = url.indexOf(".com");
        if (index != -1) {
            return url.substring(0, index + 4);
        }
        return url;
    }
}
