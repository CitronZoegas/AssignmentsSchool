package Number4;

public class Consumer extends Thread {

    private SharedBuffer buffer;
    private Controller controller;
    private int length;
    private String textInFile;
    private String replacement;

    public Consumer(Controller controller, SharedBuffer buffer, int length, String replacement) {
        this.controller = controller;
        this.buffer = buffer;
        this.length = length;
        this.replacement = replacement;
        textInFile = "";
        start();

    }

    @Override
    public void run() {
        try {
            while(true) {

                for (int i = 0; i < length; i++) {
                    StringBuilder sb = new StringBuilder();
                    textInFile += sb;
                    System.out.println(sb);
                    sb.append(buffer.ReadFile());
                    controller.newTextArea(sb.toString());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
