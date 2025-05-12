package com.endava.training.mortgage_pages.Helper;

public class ExtractValue {
    public String extractNumericValue(String input) {
        return input.replaceAll("[^\\d.]", "");
    }
}
