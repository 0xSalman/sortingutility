package com.hubhead.utilities.sort.algorithms.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsStrategy;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class RadixSort implements AlgorithmsStrategy {

    /**
     * Collator is not needed because this is not a comparison algorithm
     */
    @Override
    public String[] sort(String[] words, Collator collator) {

        System.out.println("Start RadixSort.sort()");
        radixQuickSort(words, 0, words.length - 1, 0);
        System.out.println("End RadixSort.sort()");
        return words;
    }

    /**
     * MSD 3-way radix quick sort - should be faster than normal MSD radix sort and quick sort
     * @param words
     * @param lo
     * @param hi
     * @param d
     */
    private void radixQuickSort(String words[], int lo, int hi, int d) {

        if (hi - lo <= 0) return;
        int i = lo - 1, j = hi;
        int p = lo - 1, q = hi;
        char chr = words[hi].charAt(d);

        // 4-way partition with equals at ends
        while (i < j) {
            while (words[++i].charAt(d) < chr) if (i == hi) break;
            while (chr < words[--j].charAt(d)) if (j == lo) break;

            if (i <= j) {
                swapValues(words, i, j);
                if (words[i].charAt(d) == chr) swapValues(words, ++p, i);
                if (words[j].charAt(d) == chr) swapValues(words, j, --q);
            }
        }

        // special case for all equals
        if (p == q) {
            if (chr != '\0') radixQuickSort(words, lo, hi, d + 1);
            return;
        }

        // swap equals back to middle
        if (words[i].charAt(d) < chr) i++;
        for (int k = lo; k <= p; k++) swapValues(words, k, j--);
        for (int k = hi; k >= q; k--) swapValues(words, k, i++);

        // sort 3 pieces recursively
        radixQuickSort(words, lo, j, d);
        if ((i == hi) && (words[i].charAt(d) == chr)) i++;
        if (chr != '\0') radixQuickSort(words, j + 1, i - 1, d + 1);
        radixQuickSort(words, i, hi, d);
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
