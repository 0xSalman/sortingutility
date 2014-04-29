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

    private Configurations configurations;

    public SpecificOrder(Configurations configurations) {
        super(OrderTypes.SPECIFIC);
        this.configurations = configurations;
    }

    @Override
    public void process() {

        System.out.println("Start SpecificOrder.process()");

        String [] list = this.readFile(configurations.getSourceFile(), configurations.getFileEncoding());

        if (list == null) {
            System.out.println(configurations.getSourceFile() + " is empty or could not read. Exiting!!");
            return;
        }
        if (list.length == 0) {
            // can stop here from processing further
            System.out.println(configurations.getSourceFile() + " is empty");
        }

        AlgorithmsContext context = new AlgorithmsContext();
        Collator collator = Collator.getInstance(configurations.getSortingLangLocal());
        list = context.sort(configurations.getAlgorithmType(), list, collator);

        this.writeToFile(configurations.getTargetFile(), list, configurations.getFileEncoding());

        System.out.println("End SpecificOrder.process()");
    }
}
