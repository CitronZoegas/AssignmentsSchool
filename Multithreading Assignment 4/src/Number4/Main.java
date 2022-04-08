package Number4;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo lnf : UIManager.getInstalledLookAndFeels()) {
                if (lnf.getName().contains("Nimbus")) {
                    UIManager.setLookAndFeel(lnf.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
        new Controller();
    }
}
