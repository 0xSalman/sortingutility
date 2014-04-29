package com.hubhead.utilities.sort.utils;

/**
 * Created by salman on 2014-04-27.
 */
public enum AlgorithmTypes {
    MERGE, QUICK, RADIX;

    public static boolean contains(String value) {
        for (AlgorithmTypes type : AlgorithmTypes.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
