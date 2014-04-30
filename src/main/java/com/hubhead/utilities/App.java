package com.hubhead.utilities;

import com.hubhead.utilities.sort.SortFactory;
import com.hubhead.utilities.sort.utils.Configurations;

/**
 * Test sorting utility
 */
public class App {

    public static void main(String[] args) throws Exception {

        Configurations configurations = new Configurations();
        configurations.initFromDefaultPropFile();

        SortFactory sortFactory = new SortFactory();
        sortFactory.process(configurations);
    }
}
