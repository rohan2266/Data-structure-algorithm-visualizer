package sortvisualiser.algorithms;
import sortvisualiser.SortArray;

/**
 * Base interface for the sort algorithms
 *
 * @author Matt Hopson
 */
public interface ISortAlgorithm {
    String getName();

    long getDelay();

    void setDelay(long delay);

    void runSort(SortArray array);

    String getName1();
}