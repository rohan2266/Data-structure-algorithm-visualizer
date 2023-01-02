package sortvisualiser.screens;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import sortvisualiser.MainApp;
import sortvisualiser.SortArray;
import sortvisualiser.algorithms.ISortAlgorithm;

public final class SortingVisualiserScreen extends Screen { // The main class for the sort visualiser GUI
    private final SortArray sortArray;
    private final ArrayList<ISortAlgorithm> sortQueue;

    /**
     * Creates the GUI
     * List of algorithms to run for visualisation
     * app The main application
     */
    public SortingVisualiserScreen(ArrayList<ISortAlgorithm> algorithms, MainApp app) {
        super(app);
        setLayout(new BorderLayout()); // sets the border of GUI window
        sortArray = new SortArray(); // creates object of SortArray class
        add(sortArray, BorderLayout.CENTER); // adds the radiobutton for the selected sorting algorithm
        sortQueue = algorithms; // adds the algorithm to teh arraylist
    }

    private void longSleep() { // used for delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void shuffleAndWait() { // method for shuffling the rearranged arrays and changing their colors
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }

    public void onOpen() {
        SwingWorker<Void, Void> swingWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (ISortAlgorithm algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    sortArray.setComplexity(algorithm.getName1());
                    sortArray.setAlgorithm(algorithm);

                    algorithm.runSort(sortArray);
                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                    longSleep();
                }
                return null;
            }

            @Override
            public void done() {
                app.popScreen();
            }
        };

        swingWorker.execute();
    }
}
