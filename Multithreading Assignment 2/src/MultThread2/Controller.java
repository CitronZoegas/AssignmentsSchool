package MultThread2;

import java.io.IOException;
import java.util.ArrayList;


public class Controller {

    private Display display;
    private GUIAssignment2 gui;
    private String filePath = "resources/Random words.txt";
    private ArrayList<String> listWords;

    private WordBox wordBox = new WordBox(this,filePath,listWords);

    public Controller() throws IOException {

        gui = new GUIAssignment2(this);
        gui.InitializeUI();
        //wordBox.start();
    }
    public void checkSpelling(String letterSequence) {
        if(letterSequence ==null){

        }
    }
    public void randomTextInit(String text, int x, int y) {
        gui.randomTextArea(text, x, y);
    }

}