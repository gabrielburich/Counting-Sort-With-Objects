package br.univali.kob.countingsort.object;

/**
 * Interface for objects that can be sorted by
 * counting sort
 */
public interface CountingSortAble {

    /**
     * The ordering will be based on this value
     * @return key to sort
     */
    int sortValue();
}
