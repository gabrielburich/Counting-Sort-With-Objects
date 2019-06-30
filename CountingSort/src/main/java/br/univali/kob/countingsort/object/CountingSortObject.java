package br.univali.kob.countingsort.object;

/**
 * Class to order a object array
 * @param <T> Type
 */
public class CountingSortObject<T> {

    private T[] arrayToSort;
    private ArrayElement<T>[] arrayElements;
    private int[] auxCountArray;

    /**
     * Method to sort an array
     * @param arrayToSort array to be order
     * @return order array
     */
    public T[] sort(CountingSortAble[] arrayToSort) {
        initValues(arrayToSort);

        this.auxCountArray = countElementsOccurrence(this.arrayElements, this.auxCountArray);
        this.auxCountArray = sumWithPrevious(this.auxCountArray);

        Object[] sortArray =  new Object[this.arrayToSort.length];

        for (int indexArrayToSort = 0; indexArrayToSort < this.arrayToSort.length; indexArrayToSort++) {
            ArrayElement<T> element = this.arrayElements[indexArrayToSort];
            int sortArrayIndex = this.auxCountArray[element.getKey()];
            this.auxCountArray[element.getKey()]--;
            sortArray[sortArrayIndex - 1] = element.getElement();
        }

        return (T[]) sortArray;
    }

    private void initValues(CountingSortAble[] arrayToSort) {
        this.arrayToSort = (T[]) arrayToSort;
        this.arrayElements = mountArrayElements(arrayToSort);
        this.auxCountArray = new int[max(this.arrayElements) + 1];
    }

    private ArrayElement[] mountArrayElements(CountingSortAble[] arrayToSort) {
        ArrayElement[] elements = new ArrayElement[arrayToSort.length];

        for (int i = 0; i < arrayToSort.length; i++) {
            T element = (T) arrayToSort[i];
            int key = arrayToSort[i].sortValue();
            elements[i] = new ArrayElement<>(element, key);
        }

        return elements;
    }

    private int max(ArrayElement[] array) {
        int max = array[0].getKey();

        for (int index = 1; index < array.length; index++ ) {
            if (array[index].getKey() > max) {
                max = array[index].getKey();
            }
        }

        return max;
    }

    private int[] countElementsOccurrence (ArrayElement[] elements, int[] auxCount) {
        for(int i = 0; i < elements.length; i++) {
            auxCount[elements[i].getKey()]++;
        }
        return auxCount;
    }

    private int[] sumWithPrevious(int[] auxCount) {
        for (int i = 1; i < auxCount.length; i++) {
            auxCount[i] = auxCount[i] + auxCount[i - 1];
        }
        return auxCount;
    }


    /**
     * An Array element with key to sort
     * @param <T> element type
     */
    private class ArrayElement<T>{
        private final T element;
        private final int key;

        /**
         * Default class constructor
         * @param element array element
         * @param key key to using by sort
         */
        public ArrayElement(T element, int key) {
            this.element = element;
            this.key = key;
        }

        /**
         * Get element
         * @return array element
         */
        public T getElement() {
            return element;
        }

        /**
         * Get key to sort
         * @return key to using by sort
         */
        public int getKey() {
            return key;
        }
    }
}
