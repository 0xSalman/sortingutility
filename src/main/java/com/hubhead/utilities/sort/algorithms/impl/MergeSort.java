package com.hubhead.utilities.sort.algorithms.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsStrategy;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class MergeSort implements AlgorithmsStrategy {

    private Collator collator;

    @Override
    public String[] sort(String[] list, Collator collator) {

        this.collator = collator;
        mergeSort(list, 0, list.length - 1);
        return list;
    }

    private void mergeSort(String[] list, int first, int last) {

        if (first < last) {
            int mid = (first + last) / 2;
            // sort left half of the array
            mergeSort(list, first, mid);
            // sort right half of the array
            mergeSort(list, mid + 1, last);

            // merge the two halves
            merge(list, first, mid, last);
        }
    }

    private void merge(String[] list, int first, int mid, int last) {

        String[] tmpList = new String[list.length];

        // beginning and end of first subarray
        int tmpFirstArStart = first;
        int tmpFirstArEnd = mid;

        // beginning end of second subarray
        int tmpSecondArStart = mid + 1;
        int tmpSecondArEnd = last;
        int index = tmpFirstArStart;

        while((tmpFirstArStart <= tmpFirstArEnd) && (tmpSecondArStart <= tmpSecondArEnd)) {
            if (collator.compare(list[tmpFirstArStart], list[tmpSecondArStart]) < 0) {
                tmpList[index] = list[tmpFirstArStart];
                tmpFirstArStart++;
            } else {
                tmpList[index] = list[tmpSecondArStart];
                tmpSecondArStart++;
            }
            index++;
        }

        // finish off the first subarray, if necessary
        while (tmpFirstArStart <= tmpFirstArEnd) {
            tmpList[index] = list[tmpFirstArStart];
            tmpFirstArStart++;
            index++;
        }

        // finish off the second subarray, if necessary
        while (tmpSecondArStart <= tmpSecondArEnd) {
            tmpList[index] = list[tmpSecondArStart];
            tmpSecondArStart++;
            index++;
        }

        // copy the result back into original array
        for (index = first; index <= last; index++) {
            list[index] = tmpList[index];
        }
    }

}
