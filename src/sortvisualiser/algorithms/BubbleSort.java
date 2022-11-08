package sortvisualiser.algorithms;
import sortvisualiser.SortArray;

//Bubble sort implementation
public class BubbleSort implements ISortAlgorithm {  //implements an interface
    private long stepDelay = 2; //Declares a private variable to be passed as parameter in setDelay method.
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();  //assigning len as size of array using arraySize() method declared in SortArray class
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) { //getvalue return the value of array, this method is declared im SortArray class
                    array.swap(j, j + 1, getDelay(), true); //user defined method, declared in SortArray class used for swapping
                }
            }
        }
    }

    @Override
    public String getName() {    // getname method to return type of sort, used in MainMenuScreen and SortingVisualizerScreen
        return "Bubble Sort";
    }
    public String getName1() {   // getname1 method to return time and space complexity, used in MainMenuScreen and SortingVisualizerScreen
        return "Time: (n^2)\n Space: O(n)";
    }

    @Override
    public long getDelay() {     // Used to delay the output
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {     // used to set the value of delay
        this.stepDelay = delay;            // this pointer is used to reference the current class instance variable
    }
}