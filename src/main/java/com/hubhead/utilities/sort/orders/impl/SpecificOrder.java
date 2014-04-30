package com.hubhead.utilities.sort.orders.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsContext;
import com.hubhead.utilities.sort.orders.SortOrder;
import com.hubhead.utilities.sort.utils.Configurations;
import com.hubhead.utilities.sort.utils.OrderTypes;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class SpecificOrder extends SortOrder {

    public SpecificOrder(Configurations configurations) {
        super(OrderTypes.SPECIFIC, configurations);
    }

    @Override
    public void process() {

        System.out.println("Start SpecificOrder.process()");

        String[] words = this.readFile();

        if (words == null) return;

        // Do not sort if list is empty
        if (words.length != 0) {
            AlgorithmsContext context = new AlgorithmsContext(this.getConfigurations().getAlgorithmType());
            Collator collator = Collator.getInstance(this.getConfigurations().getSortingLangLocal());
            words = context.sort(words, collator);
        } else {
            System.out.println("No sorting because the source file " + this.getConfigurations().getSourceFile() + " is empty");
        }

        this.writeToFile(words);

        System.out.println("End SpecificOrder.process()");
    }
}
