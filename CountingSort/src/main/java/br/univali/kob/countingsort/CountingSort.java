package br.univali.kob.countingsort;

import java.util.Arrays;

public class CountingSort {

    private int[] arrayToSort;
    private int[] auxCountArray;


    public int[] sort(int[] arrayToSort) {

        initValues(arrayToSort);

        int[] sortArray = new int[this.arrayToSort.length];

        this.auxCountArray = countElementsOccurrence(this.arrayToSort, this.auxCountArray);
        this.auxCountArray = sumWithPrevious(this.auxCountArray);

        for (int indexArrayToSort = 0; indexArrayToSort < this.arrayToSort.length; indexArrayToSort++) {
            int value = this.arrayToSort[indexArrayToSort];
            int sortArrayIndex = this.auxCountArray[value];
            this.auxCountArray[value]--;
            sortArray[sortArrayIndex - 1] = value;
        }
        System.out.println("AuxArray" + Arrays.toString(this.auxCountArray));

        return sortArray;
    }

    private void initValues (int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        this.auxCountArray = new int[max(arrayToSort) + 1];
    }

    private Integer max(int[] array) {
        int max = array[0];

        for (int index = 1; index < array.length; index++ ) {
            if (array[index] > max) {
                max = array[index];
            }
        }

        return max;
    }

    private int[] countElementsOccurrence (int[] elements, int[] auxCount) {
        for(int i = 0; i < elements.length; i++) {
            auxCount[elements[i]]++;
        }
        return auxCount;
    }

    private int[] sumWithPrevious(int[] auxCount) {
        for (int i = 1; i < auxCount.length; i++) {
            auxCount[i] = auxCount[i] + auxCount[i - 1];
        }
        return auxCount;
    }

}
