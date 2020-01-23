package br.univali.kob.countingsort.integer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingSortTest{

    private CountingSort countingSort;

    @Test
    public void testSort() {
        int[] arrayToSort = {1,5,6,3,1,0,5,2,4};
        int[] sortArray = {0,1,1,2,3,4,5,5,6};

        countingSort = new CountingSort();
        int[] returnArray = countingSort.sort(arrayToSort);

        assertArrayEquals(sortArray, returnArray);
    }
}
