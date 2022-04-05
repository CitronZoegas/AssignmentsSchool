package Number1;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class PlayMusic extends Thread {

    private File filepath;
    private boolean threading = false;
    private Controller controller;
    private Clip clip;

    public PlayMusic(Controller controller, File filepath) {
        this.controller = controller;
        this.filepath = filepath;
        start();
    }
    public void startMusicThread(){

        if(!threading){

            System.out.println("STARTING");
            threading = true;
        }
    }
    public void stopThread() {
        System.out.println("STOPPING");
        clip.stop();
        threading = false;

    }
    @Override
    public void run() {

        while(true) {

            try{
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (threading) {
                threading = false;

                try {
                    controller.setMusicAudioLbl("Finding best hits right now...");
                    Thread.sleep(3000);
                    controller.setMusicAudioLbl("Calibrating volume for insane experience...");
                    Thread.sleep(3000);

                    File path = null;
                    path = controller.getFilepath();
                    controller.setMusicAudioLbl(path.getName());
                    controller.setMusicTitle(path.getName());
                    File song = new File(String.valueOf(path));
                    if (song.exists()) { //checks if there actually is a dir connected to the "path" acquired.

                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(song);
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        //clip.loop(Clip.LOOP_CONTINUOUSLY); //enable to make it loop infinite

                    } else {
                        throw new RuntimeException("Sound: file not found" + song);
                    }
                } catch(Exception e){
                    JOptionPane.showMessageDialog(null, "This is not a supported audio file. Try again.");
                    JOptionPane.showMessageDialog(null, ".wav audio type works 100% of the time.");
                }
            }
        }
    }
}