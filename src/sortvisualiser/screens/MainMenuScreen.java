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
    private static final Color BACKGROUND_COLOUR = Color.BLUE; //defining color of background
    private final ArrayList<AlgorithmCheckBox> checkBoxes; 

    public MainMenuScreen(MainApp app) {
        super(app);
        checkBoxes = new ArrayList<>(); //array that contains the checkboxes
        setUpGUI();
    }

    private void addCheckBox(ISortAlgorithm algorithm, JPanel panel) {
        JRadioButton box = new JRadioButton("", true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box)); //adding algorithms to these checkboxes
        panel.add(box);
    }

    private void initContainer(JPanel p) {
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(BACKGROUND_COLOUR);
    }

    public void setUpGUI() {
        JPanel sortAlgorithmContainer = new JPanel(); 
        JPanel optionsContainer = new JPanel();
        JPanel outerContainer = new JPanel();
        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgorithmContainer);

        outerContainer.setBackground(BACKGROUND_COLOUR);
        outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));

        try {
            ClassLoader loader = getClass().getClassLoader(); //ClassLoader is used to load the classes at run time
            BufferedImage image = ImageIO.read(new File(loader.getResource("logo.png").getFile())); //why this logo necessary?
            JLabel label = new JLabel(new ImageIcon(image));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(label);
        } catch (IOException e) {
            System.out.println("Unable to load logo");
        }

        sortAlgorithmContainer.setAlignmentX(Component.LEFT_ALIGNMENT);//Examples of components are the buttons, checkboxes, and scrollbars of a typical graphical user interface
        addCheckBox(new BubbleSort(),       sortAlgorithmContainer);
        addCheckBox(new SelectionSort(),    sortAlgorithmContainer);
        addCheckBox(new QuickSort(),        sortAlgorithmContainer);
        addCheckBox(new MergeSort(),        sortAlgorithmContainer);
        addCheckBox(new InsertionSort(),    sortAlgorithmContainer);
        addCheckBox(new RadixSort(),        sortAlgorithmContainer);

        JButton startButton = new JButton("Start"); //button text
        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<ISortAlgorithm> algorithms = new ArrayList<>();
            for (AlgorithmCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            app.pushScreen(
                    new SortingVisualiserScreen(
                            algorithms,
                            app
                    ));
        });
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        outerContainer.add(optionsContainer);
        outerContainer.add(Box.createRigidArea(new Dimension(5,0)));
        outerContainer.add(sortAlgorithmContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(outerContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);
    }

    @Override
    public void onOpen() {
        checkBoxes.forEach((box) -> {
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

}