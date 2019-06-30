package br.univali.kob.countingsort.integer;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Class to order an int array
 */
public class CountingSort {

    private int[] arrayToSort;
    private int[] auxCountArray;
    static Logger logger = Logger.getLogger(CountingSort.class);

    /**
     * Method to order an int array
     * @param arrayToSort int array
     * @return int sort array
     */
    public int[] sort(int[] arrayToSort) {
        logger.debug("Array to Sort" + Arrays.toString(arrayToSort));

        initValues(arrayToSort);

        int[] sortArray = new int[this.arrayToSort.length];

        this.auxCountArray = countElementsOccurrence(this.arrayToSort, this.auxCountArray);
        logger.debug("Aux Array before countElementsOccurrence " + Arrays.toString(this.auxCountArray));
        this.auxCountArray = sumWithPrevious(this.auxCountArray);
        logger.debug("Aux Array before sumWithPrevious " + Arrays.toString(this.auxCountArray));

        for (int indexArrayToSort = 0; indexArrayToSort < this.arrayToSort.length; indexArrayToSort++) {
            int value = this.arrayToSort[indexArrayToSort];
            int sortArrayIndex = this.auxCountArray[value];
            this.auxCountArray[value]--;
            sortArray[sortArrayIndex - 1] = value;
        }

        logger.debug("Aux Array before order " + Arrays.toString(this.auxCountArray));
        logger.debug("Sort Array" + Arrays.toString(sortArray));

        return sortArray;
    }

    private void initValues (int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        this.auxCountArray = new int[max(arrayToSort) + 1];
    }

    private int max(int[] array) {
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
