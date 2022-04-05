package MultThread2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUIAssignment2 extends JPanel implements ActionListener {

    private JFrame frame;		        // The Main window

    //private JPanel pnlMove;		    // The panel to move display in
    private JTextArea pointCounter; 	// Display points
    private TextField wordTextField;
    private TextField answerTextField;
    private JComboBox cmbSkill;	        // Skill combo box, needs to be filled in
    private JPanel pnlDisplay;
    private Controller controller;
    int x = 0, y = 0;
    int xPlusTwo = 2;
    int yPlusTwo = 2;
    private int width = 1100;
    private int height = 600;

    public GUIAssignment2(Controller controller) throws IOException {
        this.controller = controller;
    }

    public void InitializeUI() throws IOException {

        frame = new JFrame();
        frame.setBounds(0, 0, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TypeGame");
        InitializeGUI();					// Fill in components
        frame.setVisible(true);
        frame.setResizable(false);			// Prevent user from change size
        frame.setLocationRelativeTo(null);	// Start middle screen
        startApp();
    }

    private void InitializeGUI()  {
        // The moving display outer panel
        pnlDisplay = new JPanel();
        pnlDisplay.setBounds(0, 0, width, height);
        pnlDisplay.setLayout(null);

        answerTextField = new TextField();
        answerTextField.setSize(150,20);
        answerTextField.setLocation(width/2,height-100);
        frame.add(answerTextField);
        frame.add(pnlDisplay);
}
    public void startApp() {
        //1000 millisekunder delay, ActionEvent e lambda
        Timer t = new Timer(1000, e -> {
            Display display = null;
            try {
                display = new Display(controller, x, y,xPlusTwo,yPlusTwo);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            display.start();

            updateUI();
        });
        t.start();
    }
    public void repaintGUI(){
        repaint();
    }
    public void updateGUI()
    {
        y = y + 10;
    }

    /*public void updateTextField(int xLoc,int yLoc) {
        wordTextField.setLocation( xLoc,yLoc);
    }*/

    public void removeTextField(Thread thread) {
        thread.stop();
    }

    /*public void randomTextArea(String text, int x,int y) {
        wordTextField = new TextField(text);
        wordTextField.setLocation(x,y);
        wordTextField.setSize(10*text.length(),40);
        wordTextField.setBackground(Color.RED);
        wordTextField.setForeground(Color.WHITE);
        frame.add(wordTextField);
        controller.checkSpelling(wordTextField.getText());
        //removeTextField(Thread.currentThread());
        frame.remove(wordTextField);

    }*/



    public void randomTextArea(String text, int x,int y) {
        Graphics graphics = pnlDisplay.getGraphics();
        //graphics.clearRect(1,2,200,200);
        graphics.drawChars(text.toCharArray(),0,text.length(),x,y);
    }

    public void removeTextArea(){

    }

    public void moveWordDownwards(double x, double y) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }


}