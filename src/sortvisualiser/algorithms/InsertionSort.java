package sortvisualiser.algorithms;
import sortvisualiser.SortArray;

public class InsertionSort implements ISortAlgorithm {
    private long stepDelay = 1;         //Declares a private variable to be passed as parameter in setDelay method.
    @Override
    public void runSort(SortArray array) {
        for (int i = 0; i < array.arraySize(); i++) {      // arraySize() method declared in SortArray class used to return the array size
            int key = array.getValue(i);                   //getvalue return the value of array, declared im SortArray class
            int j = i - 1;
            while (j >= 0 && array.getValue(j) > key) {
                array.updateSingle(j + 1, array.getValue(j), 5, true); //updateSingle method to change the value of a index
                j--;
            }
            array.updateSingle(j + 1, key, getDelay(), true); //updateSingle method declared in SortArray
        }
    }
    @Override
    public String getName() {                // getname method to return type of sort, used in MainMenuScreen and SortingVisualizerScreen
        return "Insertion Sort";
    }
    public String getName1() {              // getname1 method to return time and space complexity, used in MainMenuScreen and SortingVisualizerScreen
        return "Time: (n^2)\n Space: O(1)";
    }

    @Override
    public long getDelay() {                // Used to return value of delay
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {      // used to set the value of delay
        this.stepDelay = delay;             // this pointer is used to reference the current class instance variable
    }
}