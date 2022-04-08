package Number4;
/**
 * * Modifier calls @link BoundedBuffer#modify  a number of times.
 *
 * * * @Petter knutsson */


public class Modifier extends Thread {

    private SharedBuffer buffer;
    private int length;/** * Constructs a Modifier. * *
     @param buffer the buffer to work with *
     @param length the number of times to call modify */

    public Modifier(SharedBuffer buffer, int length) {
        this.buffer = buffer;
        this.length = length;

    }
    @Override
    public void run() {
        for (int i = 0; i < length; i++) {

            buffer.modify();

        }
    }
}
