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

        String[] words = this.readFile();

        if (words == null) return;

        // Do not sort if list is empty
        if (words.length != 0) {
            AlgorithmsContext context = new AlgorithmsContext(this.getConfigurations().getAlgorithmType());
            Collator collator = Collator.getInstance();
            words = context.sort(words, collator);
        } else {
            System.out.println("No sorting because the source file " + this.getConfigurations().getSourceFile() + " is empty");
        }

        this.writeToFile(words);

        System.out.println("End LocalOrder.process()");
    }
}
