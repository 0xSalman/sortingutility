package com.hubhead.utilities.sort.algorithms.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsStrategy;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class RadixSort implements AlgorithmsStrategy {

    @Override
    public String[] sort(String[] list, Collator collator) {

        return null;
    }

    private static void quicksortX(String a[], int lo, int hi, int d)
    {
        if (hi - lo <= 0) return;
        int i = lo-1, j = hi;
        int p = lo-1, q = hi;
        char v = a[hi].
                charAt(d);
        while (i < j)
        {
            while (a[++i].
                    charAt(d)
                    < v) if (i == hi) break;
            while (v < a[--j]
                    .charAt(d)
                    ) if (j == lo) break;
            if (i > j) break;
            exch(a, i, j);
            if (a[i]
                    .charAt(d)
                    == v) exch(a, ++p, i);
            if (a[j]
                    .charAt(d)
                    == v) exch(a, j, --q);
        }
        if (p == q)
        {
            if (v != '\0') quicksortX(a, lo, hi, d+1);
            return;
        }
        if (a[i].charAt(d) < v) i++;
        for (int k = lo; k <= p; k++) exch(a, k, j--);
        for (int k = hi; k >= q; k--) exch(a, k, i++);

        quicksortX(a, lo, j,
                d
        );
        if ((i == hi) && (a[i].charAt(d) == v)) i++;
        if (v != '\0')
            quicksortX(a, j+1, i-1,
                    d+1
            );

        quicksortX(a, i, hi,
                d
        );
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //http://www.cs.princeton.edu/~rs/AlgsDS07/18RadixSort.pdf
    //http://algs4.cs.princeton.edu/51radix/MSD.java.html

}
