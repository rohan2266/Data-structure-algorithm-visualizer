package sortvisualiser.algorithms;
import sortvisualiser.SortArray;
public class SelectionSort implements ISortAlgorithm {

    private long stepDelay = 120;      //Declares a private variable to be passed as parameter in setDelay method.
    @Override
    public void runSort(SortArray array) { //runSort method defined in interface and gets and array as input
        int len = array.arraySize();       // arraySize() method declared in SortArray class used to return the array size
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array.getValue(j) < array.getValue(minIndex)) {  //getvalue return the value of array, this method is declared im SortArray class
                    minIndex = j;
                }
            }
            array.swap(i, minIndex, getDelay(), true); //user defined method, declared in SortArray class used for swapping
        }
    }

    @Override
    public String getName() {             // getname method to return type of sort, used in MainMenuScreen and SortingVisualizerScreen
        return "Selection Sort";
    }
    public String getName1() {             // getname1 method to return time and space complexity, used in MainMenuScreen and SortingVisualizerScreen
        return "Time: (n^2)\n Space: O(1)";
    }

    @Override
    public long getDelay() {                // Used to return value of delay
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {       // used to set the value of delay
        this.stepDelay = delay;              // this pointer is used to reference the current class instance variable
    }
}