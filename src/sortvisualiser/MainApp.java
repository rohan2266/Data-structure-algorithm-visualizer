package sortvisualiser;

import sortvisualiser.screens.MainMenuScreen;
import sortvisualiser.screens.Screen;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainApp {
    private final JFrame window;/*JFrame is a class of the javax.swing package. It provides GUI interface
                                 Frame works like the main window where components like labels, buttons, textfields are added to create a GUI.*/
    public static final int WIN_WIDTH = 1280; // Assigning width of main frame window
    public static final int WIN_HEIGHT = 720; //Assigning height of main frame window

    private final ArrayList<Screen> screens; // created a arraylist of type Screen

    public MainApp() {
        screens = new ArrayList<>();  // Initialising a new arraylist of screens
        window = new JFrame ("Sort visualiser"); // Heading of GUI window
        window.setVisible(true); // For making the GUI visible or not
    }

    public Screen getCurrentScreen() { //returns current screen
        return screens.get(screens.size() - 1);
    }

    public void pushScreen(Screen screen) {   //initialised a method to push screen that takes object of Screen class as parameter
        if (!screens.isEmpty()) {             // if screen is not empty then remove the current screen from GUI
            window.remove(getCurrentScreen());
        }
        screens.add(screen);                   // If screen is empty, add a new screen to GUi
        window.setContentPane(screen);         // Set the content pane through the screen object
        window.validate();                     //Inbuilt function
        screen.onOpen();
    }

    public void popScreen() {               //initiated a method to pop the window and screen on GUI
        if (!screens.isEmpty()) {           // if screen is not empty then remove the window and screen
            Screen prev = getCurrentScreen();
            screens.remove(prev);
            window.remove(prev);
            if (!screens.isEmpty()) {
                Screen current = getCurrentScreen();
                window.setContentPane(current);
                window.validate();
                current.onOpen();
            }
            else {
                window.dispose();
            }
        }
    }

    public void start() {      // starts and push the main menu screen onto the GUI
        pushScreen(new MainMenuScreen(this));
        window.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().start();
        });
    }
}
