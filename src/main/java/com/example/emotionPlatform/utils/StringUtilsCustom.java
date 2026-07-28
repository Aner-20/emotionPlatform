package com.example.emotionPlatform.utils;

public class StringUtilsCustom {
    public static String capitalizeWords(String input){
        if (input == null || input.isBlank()){
            return input;
        }

        String[] words = input.trim().toLowerCase().split("\\s+");

        StringBuilder result = new StringBuilder();
        for (String word : words){
            if (!word.isEmpty()){
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        
        return result.toString().trim();
    }
}
