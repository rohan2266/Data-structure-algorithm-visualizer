package sortvisualiser.algorithms;
import sortvisualiser.SortArray;

public interface ISortAlgorithm {
    String getName();

    long getDelay();

    void setDelay(long delay);

    void runSort(SortArray array);

    String getName1();
}