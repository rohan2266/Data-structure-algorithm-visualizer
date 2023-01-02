package sortvisualiser.screens;

import static sortvisualiser.MainApp.WIN_HEIGHT;
import static sortvisualiser.MainApp.WIN_WIDTH;
import java.awt.Dimension;
import javax.swing.JPanel;
import sortvisualiser.MainApp;

public abstract class Screen extends JPanel { // making a child class from parent class JPanel
    protected MainApp app; // protected keyword is an access modifier used for attributes,
    // methods and constructors, making them accessible in the same package and
    // subclasses

    public Screen(MainApp app) {
        this.app = app; // setter function
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT); // return dimension of window
    }

    public abstract void onOpen(); // abstract onOpen method used for starting a sort ting window from beginning
}
