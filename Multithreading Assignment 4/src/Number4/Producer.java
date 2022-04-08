package Number4;

public class Producer implements Runnable{

    private SharedBuffer buffer;
    private String[] writeText;

    public Producer(SharedBuffer buffer, String[] writing) {

        this.buffer = buffer;
        this.writeText = writing;
    }
    @Override
    public void run() {
        for(String sIndex: writeText){
            buffer.writeBuffer(sIndex);
        }
    }
}
