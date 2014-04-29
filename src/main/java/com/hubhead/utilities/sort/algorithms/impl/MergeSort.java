package com.hubhead.utilities.sort.algorithms.impl;

import com.hubhead.utilities.sort.algorithms.AlgorithmsStrategy;

import java.text.Collator;

/**
 * Created by salman on 2014-04-27.
 */
public class MergeSort implements AlgorithmsStrategy {

    private Collator collator;

    @Override
    public String[] sort(String[] words, Collator collator) {

        System.out.println("Start MergeSort.sort()");
        this.collator = collator;
        mergeSort(words, 0, words.length - 1);
        System.out.println("End MergeSort.sort()");
        return words;
    }

    private void mergeSort(String[] words, int first, int last) {

        if (first < last) {
            int mid = (first + last) / 2;
            // sort left half of the array
            mergeSort(words, first, mid);
            // sort right half of the array
            mergeSort(words, mid + 1, last);

            // merge the two halves
            merge(words, first, mid, last);
        }
    }

    private void merge(String[] words, int first, int mid, int last) {

        String[] tmpList = new String[words.length];

        // beginning and end of first subarray
        int tmpFirstArStart = first;
        int tmpFirstArEnd = mid;

        // beginning end of second subarray
        int tmpSecondArStart = mid + 1;
        int tmpSecondArEnd = last;
        int index = tmpFirstArStart;

        while((tmpFirstArStart <= tmpFirstArEnd) && (tmpSecondArStart <= tmpSecondArEnd)) {
            if (collator.compare(words[tmpFirstArStart], words[tmpSecondArStart]) < 0) {
                tmpList[index] = words[tmpFirstArStart];
                tmpFirstArStart++;
            } else {
                tmpList[index] = words[tmpSecondArStart];
                tmpSecondArStart++;
            }
            index++;
        }

        // finish off the first subarray, if necessary
        while (tmpFirstArStart <= tmpFirstArEnd) {
            tmpList[index] = words[tmpFirstArStart];
            tmpFirstArStart++;
            index++;
        }

        // finish off the second subarray, if necessary
        while (tmpSecondArStart <= tmpSecondArEnd) {
            tmpList[index] = words[tmpSecondArStart];
            tmpSecondArStart++;
            index++;
        }

        // copy the result back into original array
        for (index = first; index <= last; index++) {
            words[index] = tmpList[index];
        }

        // attempt to free memory
        tmpList = null;
    }

}
