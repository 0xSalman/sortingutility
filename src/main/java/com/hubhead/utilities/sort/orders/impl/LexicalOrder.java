package com.hubhead.utilities.sort.orders.impl;

import com.hubhead.utilities.sort.orders.SortOrder;
import com.hubhead.utilities.sort.utils.Configurations;
import com.hubhead.utilities.sort.utils.OrderTypes;

import java.util.Arrays;

/**
 * Created by salman on 2014-04-27.
 */
public class LexicalOrder extends SortOrder {

    public LexicalOrder(Configurations configurations) {
        super(OrderTypes.LEXICAL, configurations);
    }

    @Override
    public void process() {

        System.out.println("Start LexicalOrder.process()");

        String[] words = this.readFile();

        if (words == null) {
            System.out.println(this.getConfigurations().getSourceFile() + " is empty or could not read. Exiting!!");
            return;
        }
        if (words.length == 0) {
            // can stop here from processing further
            System.out.println(this.getConfigurations().getSourceFile() + " is empty");
        }

        /* not using custom sorting because java sort by default
         *  is UTF-16 based and uses modified version of merge sort
         *  so it should be good
         */
        Arrays.sort(words);
        this.writeToFile(words);

        System.out.println("End LexicalOrder.process()");
    }
}
