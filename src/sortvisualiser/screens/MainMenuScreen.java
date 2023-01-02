package sortvisualiser.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import sortvisualiser.MainApp;
import sortvisualiser.algorithms.*;

public final class MainMenuScreen extends Screen {
    private static final Color BACKGROUND_COLOUR = Color.BLUE; // sets the background color
    private final ArrayList<AlgorithmCheckBox> checkBoxes; // creates an arraylist for the checkboxes in the GUI screen
    private final ArrayList<GraphAlgorithmCheckBox> graphCheckBoxes; // creates an arraylist for the checkboxes in the
                                                                     // GUI
                                                                     // screen

    public MainMenuScreen(MainApp app) {
        super(app);
        checkBoxes = new ArrayList<>(); // we initialised the checkbox arraylist
        graphCheckBoxes = new ArrayList<>(); // we initialised the checkbox arraylist
        setUpGUI(); // called the setUpGUI method defined below
    }

    private void addCheckBox(ISortAlgorithm algorithm, JPanel panel) { // add the checkboxes for sorting algos in the
                                                                       // mainMenuScreen of GUI window
        JRadioButton box = new JRadioButton("", true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box)); // adding algorithms to these checkboxes
        panel.add(box);
    }

    private void graphAddCheckBox(MouseGraph m, JPanel panel) { // add the checkboxes for sorting algos in the
        // mainMenuScreen of GUI window
        JRadioButton box = new JRadioButton("", true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        graphCheckBoxes.add(new GraphAlgorithmCheckBox(m, box)); // adding algorithms to these checkboxes
        panel.add(box);
    }

    private void initContainer(JPanel p) { // Container class init() method
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(BACKGROUND_COLOUR);
    }

    public void setUpGUI() { // method created to setup the GUI window, i.e setting background and alignment
        JPanel sortAlgorithmContainer = new JPanel(); // object for sorting array window
        JPanel optionsContainer = new JPanel(); // object for main menu class i.e, with the radio buttons for sort algos
        JPanel outerContainer = new JPanel();
        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgorithmContainer);

        outerContainer.setBackground(BACKGROUND_COLOUR); // set the background color
        outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS)); // set the layout

        sortAlgorithmContainer.setAlignmentX(Component.LEFT_ALIGNMENT);// Examples of components are the buttons,
                                                                       // checkboxes, and scrollbars of a typical
                                                                       // graphical user interface
        addCheckBox(new BubbleSort(), sortAlgorithmContainer);
        addCheckBox(new SelectionSort(), sortAlgorithmContainer);
        addCheckBox(new QuickSort(), sortAlgorithmContainer);
        addCheckBox(new MergeSort(), sortAlgorithmContainer);
        addCheckBox(new InsertionSort(), sortAlgorithmContainer);
        addCheckBox(new RadixSort(), sortAlgorithmContainer);
        graphAddCheckBox(new MouseGraph(false), sortAlgorithmContainer);

        JButton startButton = new JButton("Start"); // start button for mainmenu in GUI
        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<ISortAlgorithm> algorithms = new ArrayList<>();
            ArrayList<MouseGraph> graphAlgorithms = new ArrayList<>();
            Boolean graph = false;

            for (AlgorithmCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            // print myCheckBoxes
            for (GraphAlgorithmCheckBox cb : graphCheckBoxes) {
                // MyAlgorithmCheckBox cb = graphCheckBoxes[0];
                if (cb.isSelected()) {
                    graph = true;
                    algorithms.clear();
                    graphAlgorithms.add(cb.getAlgorithm());
                }
            }
            if (graph) {
                new MouseGraph(true);
            } else {

                app.pushScreen(
                        new SortingVisualiserScreen(
                                algorithms,
                                app));
            }
        });
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        outerContainer.add(optionsContainer);
        outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
        outerContainer.add(sortAlgorithmContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(outerContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);
    }

    @Override
    public void onOpen() { // unchecks all the radiobutton
        checkBoxes.forEach((box) -> {
            box.unselect();
        });
        graphCheckBoxes.forEach((box) -> {
            box.unselect();
        });

    }

    private class AlgorithmCheckBox {
        private final ISortAlgorithm algorithm;
        private final JRadioButton box;

        public AlgorithmCheckBox(ISortAlgorithm algorithm, JRadioButton box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }

        public void unselect() {
            box.setSelected(false);
        }

        public boolean isSelected() {
            return box.isSelected();
        }

        public ISortAlgorithm getAlgorithm() {
            return algorithm;
        }
    }

    private class GraphAlgorithmCheckBox {
        private final MouseGraph algorithm;
        private final JRadioButton box;

        public GraphAlgorithmCheckBox(MouseGraph algorithm, JRadioButton box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }

        public void unselect() {
            box.setSelected(false);
        }

        public boolean isSelected() {
            return box.isSelected();
        }

        public MouseGraph getAlgorithm() {
            return algorithm;
        }
    }

}
