/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Parser {

    public static Integer parseIntSafe(String target, Integer defaultValue) {
        if (target == null) {
            return defaultValue;
        }

        Integer number = defaultValue;
        try {
            number = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        return number;
    }

    public static Float parseFloatSafe(String target, Float defaultValue) {
        if (target == null) {
            return defaultValue;
        }

        Float number = defaultValue;
        try {
            number = Float.parseFloat(target);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        return number;
    }

    public static Boolean parseBooleanSafe(String target, Boolean defaultValue) {
        if (target == null) {
            return defaultValue;
        }

        Boolean bool = Boolean.parseBoolean(target);
        return bool;
    }

    public static String[] parseString(String target, String separator) {
        if (target == null) {
            return new String[0];
        }

        return target.split(separator);
    }

    public static String[] parseStringWithFilledNull(String target, String separator, int expectedArrayLength) {
        String[] splittedString = parseString(target, separator);

        if (splittedString.length < expectedArrayLength) {
            return new String[expectedArrayLength];
        }

        return splittedString;
    }

    public static int[] parseStringToIntegerArray(String target, String separator) {
        if (target == null) {
            return new int[0];
        }

        String[] strArray = target.split(separator);
        List<Integer> listInteger = new ArrayList<>();

        for (String item : strArray) {
            int number = -1;

            try {
                number = Integer.parseInt(item);
            } catch (NumberFormatException e) {
                System.err.println(e);
            }

            listInteger.add(number);
        }

        return listInteger.stream().mapToInt(Integer::intValue).toArray();
    }
}
