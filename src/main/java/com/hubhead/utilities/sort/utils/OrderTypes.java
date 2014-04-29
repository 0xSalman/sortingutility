package com.hubhead.utilities.sort.utils;

/**
 * Created by salman on 2014-04-27.
 */
public enum OrderTypes {
    LEXICAL, LOCAL, SPECIFIC;

    public static boolean contains(String value) {
        for (OrderTypes type : OrderTypes.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
