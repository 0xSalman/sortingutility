package com.hubhead.utilities.sort.orders.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsContext;
import com.hubhead.utilities.sort.orders.SortOrder;
import com.hubhead.utilities.sort.utils.Configurations;
import com.hubhead.utilities.sort.utils.OrderTypes;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class LocalOrder extends SortOrder {

    public LocalOrder(Configurations configurations) {
        super(OrderTypes.LOCAL, configurations);
    }

    @Override
    public void process() {

        System.out.println("Start LocalOrder.process()");

        String[] list = this.readFile();

        if (list == null) {
            System.out.println(this.getConfigurations().getSourceFile() + " is empty or could not read. Exiting!!");
            return;
        }
        if (list.length == 0) {
            // can stop here from processing further
            System.out.println(this.getConfigurations().getSourceFile() + " is empty");
        }

        AlgorithmsContext context = new AlgorithmsContext();
        Collator collator = Collator.getInstance();
        list = context.sort(this.getConfigurations().getAlgorithmType(), list, collator);

        this.writeToFile(list);

        System.out.println("End LocalOrder.process()");
    }
}
