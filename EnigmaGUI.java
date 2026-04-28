import javax.swing.*;


public class EnigmaGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EnigmaFrame();
            }
        });
    }
}