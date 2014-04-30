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
    private AlgorithmTypes strategy;

    public AlgorithmsContext(AlgorithmTypes strategy) {
        this.strategy = strategy;
        strategies = new HashMap<AlgorithmTypes, AlgorithmsStrategy>();
        setStrategies();
    }

    private void setStrategies() {
        strategies.put(AlgorithmTypes.MERGE, new MergeSort());
        strategies.put(AlgorithmTypes.QUICK, new QuickSort());
        strategies.put(AlgorithmTypes.RADIX, new RadixSort());
    }

    public String[] sort(String[] words, Collator collator) {
        return strategies.get(strategy).sort(words, collator);
    }
}
