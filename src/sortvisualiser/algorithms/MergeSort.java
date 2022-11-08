package sortvisualiser.algorithms;
import sortvisualiser.SortArray;
public class MergeSort implements ISortAlgorithm {
    private long stepDelay = 20;        //Declares a private variable to be passed as parameter in setDelay method.

    private int[] getSubArray(SortArray array, int begin, int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array.getValue(begin + i);
        }
        return arr;
    }

    /*
     * This is the core of the algorithm merge sort,
     * take the array from input and do the cut and merge things.
     *  array  this is the array to cut and merge
     *  left   the left index of the array
     *  middle the middle index of the array
     * right  the right index of the array
     */
    private void merge(SortArray array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int leftArray[] = getSubArray(array, left, leftSize);
        int rightArray[] = getSubArray(array, middle + 1, rightSize);

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array.updateSingle(k, leftArray[i], getDelay(), true);
                i++;
            } else {
                array.updateSingle(k, rightArray[j], getDelay(), true);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array.updateSingle(k, leftArray[i], getDelay(), true);
            i++;
            k++;
        }

        while (j < rightSize) {
            array.updateSingle(k, rightArray[j], getDelay(), true);
            j++;
            k++;
        }
    }
    private void mergeSort(SortArray array, int left, int right) {
        if (left < right) {
            int middleIndex = (left + right) / 2;

            mergeSort(array, left, middleIndex);
            mergeSort(array, middleIndex + 1, right);
            merge(array, left, middleIndex, right);
        }
    }
    @Override
    public void runSort(SortArray array) {
        int left = 0;
        int right = array.arraySize() - 1;
        mergeSort(array, left, right);
    }

    @Override
    public String getName() {      // getname method to return type of sort, used in MainMenuScreen and SortingVisualizerScreen
        return "Merge Sort";
    }
    public String getName1() {      // getname1 method to return time and space complexity, used in MainMenuScreen and SortingVisualizerScreen
        return "Time: (nlogn)\n Space: O(n)";
    }

    @Override
    public long getDelay() {         // Used to return value of delay
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) { // used to set the value of delay
        this.stepDelay = delay;        // this pointer is used to reference the current class instance variable
    }
}