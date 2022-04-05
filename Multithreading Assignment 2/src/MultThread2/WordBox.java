package MultThread2;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class WordBox {

private Controller controller;
private String filePath;
private ArrayList<String> listWords;
private Random rng;

public WordBox(Controller controller, String filePath, ArrayList<String> listWords) {
    this.controller = controller;
    this.filePath = filePath;
    this.listWords = listWords;
     rng = new Random();
}

    //@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public String getRandomWord() throws IOException {

        try {

            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            listWords = new ArrayList<>();
            String strTemp;

            while((strTemp = br.readLine())!= null) {
                listWords.add(strTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int randomized = rng.nextInt(listWords.size());
        return listWords.get(randomized);
    }
}