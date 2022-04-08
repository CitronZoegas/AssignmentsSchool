package Number4;

public class SharedBuffer {
    private String findDifference;
    private String replaceString;

    private Status[] status;
    private String[] maxSize;

    private int length;
    private int writePos = 0;
    private int readPos  = 0;
    private int findPos  = 0;




    public SharedBuffer(int length, String findDifference, String replaceString) {
        status = new Status[length];
        maxSize = new String[length];
        if(length < 0){
            throw new IllegalArgumentException("Text must be > 0");
        }

        this.length = length;
        this.findDifference = findDifference;
        this.replaceString = replaceString;

        for (int i = 0; i <status.length ; i++) {
            status[i] = Status.EMPTY;
        }
    }

    public synchronized String ReadFile() {

        while(status[readPos] != Status.CHECKED){
            try{
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String singleElement = maxSize[readPos];
        status[readPos++] = Status.EMPTY;
        readPos %= singleElement.length();
        notifyAll();

        return singleElement;
    }
    public synchronized void writeBuffer(String stringPos) {
        while(status[writePos] != Status.EMPTY){
            try{
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        status[writePos] = Status.NEW;

        maxSize[writePos] = stringPos;
        writePos = (writePos+1) % maxSize.length;
        notifyAll();
    }
    public synchronized void modify() {
        while(status[findPos] != Status.NEW){
            try{
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(findDifference + replaceString);

        maxSize[findPos] = maxSize[findPos].replace(findDifference, replaceString);
        status[findPos] = Status.CHECKED;
        findPos = (findPos++) % maxSize.length;
        notifyAll();
    }

    private enum Status {
        EMPTY, CHECKED, NEW
    }
}
