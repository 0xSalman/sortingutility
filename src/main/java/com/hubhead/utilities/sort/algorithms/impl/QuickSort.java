package com.hubhead.utilities.sort.algorithms.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsStrategy;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class QuickSort implements AlgorithmsStrategy {

    private Collator collator;

    @Override
    public String[] sort(String[] words, Collator collator) {

        System.out.println("Start QuickSort.sort()");
        this.collator = collator;
        quickSort(words, 0, words.length - 1);
        System.out.println("End QuickSort.sort()");
        return words;
    }

    private void quickSort(String words[], int low, int high) {

        int tmpLow = low, tmpHigh = high;
        // Get pivot from the middle of the list
        String pivot = words [low + (high - low) / 2];

        // two-way partition
        while (tmpLow <= tmpHigh) {
            while(collator.compare(words[tmpLow], pivot) < 0) tmpLow++;
            while(collator.compare(words[tmpHigh], pivot) > 0) tmpHigh--;

            if (tmpLow <= tmpHigh) {
                swapValues(words, tmpLow, tmpHigh);
                tmpLow++;
                tmpHigh--;
            }
        }

        // Recursion
        if (low < tmpHigh) quickSort(words, low, tmpHigh);
        if (tmpLow < high) quickSort(words, tmpLow, high);
    }

    /**
     * Swap values
     */
    private void swapValues(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }
}
