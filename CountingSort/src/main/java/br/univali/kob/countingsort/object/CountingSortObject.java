package br.univali.kob.countingsort.object;

import br.univali.kob.countingsort.integer.CountingSort;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Class to order a object array
 */
public class CountingSortObject extends CountingSort {

    private CountingSortAble[] arrayToSort;
    private int[] auxCountArray;
    static Logger logger = Logger.getLogger(CountingSortObject.class);


    /**
     * Method to sort an array
     * @param arrayToSort array to be order
     * @return order array
     */
    public Object[] sort(CountingSortAble[] arrayToSort) {
        logger.debug("Array to Sort" + Arrays.toString(arrayToSort));

        initValues(arrayToSort);

        this.auxCountArray = countElementsOccurrence(this.arrayToSort, this.auxCountArray);
        logger.debug("Aux Array after countElementsOccurrence " + Arrays.toString(this.auxCountArray));
        this.auxCountArray = sumWithPrevious(this.auxCountArray);
        logger.debug("Aux Array after sumWithPrevious " + Arrays.toString(this.auxCountArray));

        Object[] sortArray = new Object[this.arrayToSort.length];

        for (int indexArrayToSort = 0; indexArrayToSort < this.arrayToSort.length; indexArrayToSort++) {
            CountingSortAble element = this.arrayToSort[indexArrayToSort];
            int sortArrayIndex = this.auxCountArray[element.sortValue()];
            this.auxCountArray[element.sortValue()]--;
            sortArray[sortArrayIndex - 1] = element;
        }

        logger.debug("Aux Array after order " + Arrays.toString(this.auxCountArray));
        logger.debug("Sort Array" + Arrays.toString(sortArray));

        return sortArray;
    }

    private void initValues(CountingSortAble[] arrayToSort) {
        this.arrayToSort =  arrayToSort;
        this.auxCountArray = new int[max(arrayToSort) + 1];
    }

    private int max(CountingSortAble[] array) {
        int max = array[0].sortValue();

        for (int index = 1; index < array.length; index++ ) {
            if (array[index].sortValue() > max) {
                max = array[index].sortValue();
            }
        }

        return max;
    }

    private int[] countElementsOccurrence (CountingSortAble[] elements, int[] auxCount) {
        for(int i = 0; i < elements.length; i++) {
            auxCount[elements[i].sortValue()]++;
        }
        return auxCount;
    }

}
