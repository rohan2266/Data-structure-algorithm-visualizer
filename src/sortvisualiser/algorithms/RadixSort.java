package sortvisualiser.algorithms;
import java.util.Arrays;
import sortvisualiser.SortArray;

public class RadixSort implements ISortAlgorithm {
    private long stepDelay = 5;     //Declares a private variable to be passed as parameter in setDelay method.
    private int radix;
    private int[] countingArr;

    public RadixSort(int radix) {
        this.radix = radix;                 //assigns the value of radix using this keyword
        countingArr = new int[radix];       //declare CountingArr[] size
    }

    public RadixSort() //Sets Radix to base 10 by default.
    {
        this(10);
    }

    /*
     * This is the method that call the first instance of Radix Sort.
     * Radix Sort is a non comparison based algorithm that uses counting sort as a subroutine.
     * It works by sorting by the least significant digit from smallest to largest. It then
     * sorts the next least significant digit and so on. We are not limited to the decimal number
     * system however. We can sort in Hex, Binary, etc; hence the name Radix Sort.
     */
    @Override
    public void runSort(SortArray array)
    {
        int largest = array.getMaxValue();    //getMaxValue() defined in class SortArray is used to return max value of an array
        int[] result = new int[array.arraySize()];   // arraySize() defined in SortArray class it is used to return array size

        for(int exp = 1; largest/exp > 0; exp *= radix)		//in real life if Radix was 2, then we would bit shift.
        {
            countingArr = countingSort(array, exp);

            for(int i = 0; i < result.length; ++i)
                array.updateSingle(i, result[i] = array.getValue(i), getDelay(), false);  //updateSingle() is used to update value of array at a given index

            for(int i = 1; i < radix; ++i)
                countingArr[i] += countingArr[i-1];

            for(int i = array.arraySize() - 1; i > -1 ; --i)
                array.updateSingle(--countingArr[(result[i]/exp)%radix], result[i], getDelay(), true);
        }
    }

    /*
     * Performs a Counting Sort subroutine
     * arr= The array being sorted
     * exp= The current exponent
     * A counting array that gives new indices to all values
     */
    private int[] countingSort(SortArray arr, int exp)
    {
        Arrays.fill(countingArr, 0);
        for(int i = 0; i < arr.arraySize(); ++i)
            countingArr[(arr.getValue(i)/exp)%radix]++; // getValue return the value of array at that index
        return countingArr;
    }

    @Override
    public String getName() {               // getname method to return type of sort, used in MainMenuScreen and SortingVisualizerScreen
        return "Radix Sort (Base " + radix + ")";
    }
    public String getName1() {              // getname1 method to return time and space complexity, used in MainMenuScreen and SortingVisualizerScreen
        return "Time: (n*d)\n Space: O(n+2^d)";
    }

    @Override
    public long getDelay() {                 // Used to return value of delay
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {        // used to set the value of delay
        this.stepDelay = delay;               // this pointer is used to reference the current class instance variable
    }
}