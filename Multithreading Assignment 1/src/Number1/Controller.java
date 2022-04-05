package Number1;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    private boolean threading = false;
    private GUIAssignment1 gui;
    private File filepath;

    private Display display;

    private PlayMusic musicThread;

    public Controller() throws IOException {

        gui = new GUIAssignment1(this);
        gui.Start();
        musicThread = new PlayMusic(this, filepath);
        display     = new Display(this);

    }
    /** MUSIC RELATED**/
    public void startThread() {
        threading = true;
        musicThread.startMusicThread();
    }
    public void stopThread() {
        musicThread.stopThread();
    }
    public File getFilepath() {
        return filepath;
    }
    public void setMusicAudioLbl(String text) {
        gui.setAudioLbl(text);
    }
    public void setMusicTitle(String text) {
        gui.setMusicTitle(text);
    }


    /**
     * ÄNDRA DIRECTORY TILL VART DU HAR DINA .WAV eller .MP3 FILER FÖR ENKLARE SÖKNING.
     * @throws IOException
     */
    public void browseFiles() throws IOException {

        JFileChooser chooser = new JFileChooser("resources");
        chooser.showOpenDialog(null);
        filepath = chooser.getSelectedFile();
    }
    /** DISPLAY RELATED**/
    public void randomTextInit(String text, int x, int y) {
        gui.randomTextArea(text, x, y);
    }
    public void startDisplayThread() {
        display.startThread();
    }
    public void stopDisplayThread() {
        display.stopThread();
    }
}
