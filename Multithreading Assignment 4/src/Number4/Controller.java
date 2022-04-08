package Number4;

import javax.swing.*;

/**
 * Main entry for assignment 4.
 *
 * @author Petter Knutsson
 */
public class Controller {


    private MainPanel mainPanel;
    private SharedBuffer buffer;
    private Thread modifier;
    private Thread reader;
    private Thread writer;

    private JTextPane textPane;

    public Controller() {
        showFrame();
        mainPanel = new MainPanel(this);
    }
    private void showFrame() {
        mainPanel = new MainPanel(this);

        JFrame frame = new JFrame("Text File Editor by Petter Knutsson");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void newTextArea(String newText) {
        mainPanel.insertText(newText);
    }

    /**
     * Creates the bounded buffer and reader/writer/modifier threads.
     *
     * @param target where to store final result (GUI)
     * @param lines source lines
     * @param find string to find
     * @param replace replace with this string
     */
    public void execute(JTextArea target, String[] lines, String find, String replace) {
        buffer = new SharedBuffer(5, find, replace);
        System.out.println(target.getText() + "execute");
        reader = new Thread(new Consumer(this, buffer, lines.length, target.getText()), "Reader");
        writer = new Thread(new Producer( buffer, lines),"Writer");
        modifier = new Thread(new Modifier(buffer, lines.length),"Modifier");

        reader.start();
        writer.start();
        modifier.start();

        //Starta alla trådar härifrån, metoden kallas på via buffern(?).
    }
}
