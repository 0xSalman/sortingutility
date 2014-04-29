package com.hubhead.utilities.sort;

import com.hubhead.utilities.sort.orders.SortOrder;
import com.hubhead.utilities.sort.orders.impl.LexicalOrder;
import com.hubhead.utilities.sort.orders.impl.SpecificOrder;
import com.hubhead.utilities.sort.utils.Configurations;

/**
 * Created by salman on 2014-04-27.
 */
public class SortFactory {

    public void process() {

        System.out.println("Start SortFactory.process()");

        try {
            Configurations configurations = new Configurations();
            configurations.initFromDefaultPropFile();

            if (configurations.validateConfigurations()) {
                System.out.println("Configurations are valid");
                SortOrder sortOrder = sortOrderBuilder(configurations);
                sortOrder.process();
            } else {
                System.out.println("Configurations are invalid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("End SortFactory.process()");
    }

    private SortOrder sortOrderBuilder(Configurations config) {

        SortOrder sortOrder = null;

        switch (config.getOrderType()) {
            case LEXICAL:
                sortOrder = new LexicalOrder(config);
                break;
            case LOCAL:
                sortOrder = new LexicalOrder(config);
                break;
            case SPECIFIC:
                sortOrder = new SpecificOrder(config);
                break;
        }

        return sortOrder;
    }
}
