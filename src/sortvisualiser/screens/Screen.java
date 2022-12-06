package sortvisualiser.screens;
import static sortvisualiser.MainApp.WIN_HEIGHT;
import static sortvisualiser.MainApp.WIN_WIDTH;
import java.awt.Dimension;
import javax.swing.JPanel;
import sortvisualiser.MainApp;

<<<<<<< HEAD
public abstract class Screen extends JPanel { //making a child class from parent class JPanel
    protected MainApp app; //protected keyword is an access modifier used for attributes, 
    //methods and constructors, making them accessible in the same package and subclasses
=======
public abstract class Screen extends JPanel {
    protected MainApp app;   // object of MainApp class
>>>>>>> 6a5781b83e4af42e6dddad64f872c76d885b4356

    public Screen(MainApp app) {
        this.app = app;       // setter function
    }

    @Override
    public Dimension getPreferredSize() {
<<<<<<< HEAD
        return new Dimension(WIN_WIDTH, WIN_HEIGHT); //se
=======
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);    // return dimension of window
>>>>>>> 6a5781b83e4af42e6dddad64f872c76d885b4356
    }

    public abstract void onOpen(); //abstract onOpen method used for starting a sort ting window from beginning
}
