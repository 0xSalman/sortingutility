package com.hubhead.utilities.sort.algorithms;

import com.hubhead.utilities.sort.algorithms.impl.MergeSort;
import com.hubhead.utilities.sort.algorithms.impl.QuickSort;
import com.hubhead.utilities.sort.algorithms.impl.RadixSort;
import com.hubhead.utilities.sort.utils.AlgorithmTypes;

import java.text.Collator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by salman on 2014-04-27.
 */
public class AlgorithmsContext {

    private Map<AlgorithmTypes, AlgorithmsStrategy> strategies;

    public AlgorithmsContext() {
        strategies = new HashMap<AlgorithmTypes, AlgorithmsStrategy>();
        setStrategies();
    }

    private void setStrategies() {
        strategies.put(AlgorithmTypes.MERGE, new MergeSort());
        strategies.put(AlgorithmTypes.QUICK, new QuickSort());
        strategies.put(AlgorithmTypes.RADIX, new RadixSort());
    }

    public String [] sort(AlgorithmTypes strategy, String[] list, Collator collator) {
        return strategies.get(strategy).sort(list,collator);
    }
}
