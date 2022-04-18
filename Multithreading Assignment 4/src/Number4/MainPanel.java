package Number4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * User Interface.
 */
public class MainPanel extends JPanel {
    private Controller controller;

    private JPanel pnlFindReplace = new JPanel(new GridLayout(2, 2, 5, 5));
    private JLabel lblFind = new JLabel("Find:", JLabel.TRAILING);
    private JTextField tfFind = new JTextField();
    private JLabel lblReplaceWith = new JLabel("Replace with:", JLabel.TRAILING);
    private JTextField tfReplaceWith = new JTextField();

    private JPanel pnlSourceDest = new JPanel(new GridLayout(1, 2, 5, 5));

    private JPanel pnlSource = new JPanel(new BorderLayout(5, 5));
    private JLabel lblSource = new JLabel("Source:");
    private JTextArea taSource = new JTextArea();
    private JScrollPane spSource = new JScrollPane(taSource);

    private JPanel pnlDest = new JPanel(new BorderLayout(5, 5));
    private JLabel lblDest = new JLabel("Destination:");
    private JTextArea taDest = new JTextArea(200,200);
    private JScrollPane spDest = new JScrollPane(taDest);

    private JPanel pnlButtons = new JPanel(new GridLayout(1, 1, 5, 5));
    private JButton btnLoadFile = new JButton("Load file");
    private JButton btnFindReplace = new JButton("Find and replace");

    /**
     * Default constructor.
     */
    public MainPanel(Controller controller) {

        this.controller = controller;

        setPreferredSize(new Dimension(600, 350));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout(5, 5));

        pnlFindReplace.add(lblFind);
        pnlFindReplace.add(tfFind);
        pnlFindReplace.add(lblReplaceWith);
        pnlFindReplace.add(tfReplaceWith);
        add(pnlFindReplace, BorderLayout.NORTH);

        pnlSource.add(lblSource, BorderLayout.NORTH);
        pnlSource.add(spSource, BorderLayout.CENTER);

        pnlDest.add(lblDest, BorderLayout.NORTH);
        pnlDest.add(spDest, BorderLayout.CENTER);


        pnlSourceDest.add(pnlSource);
        pnlSourceDest.add(pnlDest);
        add(pnlSourceDest, BorderLayout.CENTER);

        ButtonListener listener = new ButtonListener();

        btnLoadFile.addActionListener(listener);
        btnFindReplace.addActionListener(listener);

        pnlButtons.add(btnLoadFile);
        pnlButtons.add(btnFindReplace);
        add(pnlButtons, BorderLayout.SOUTH);
    }
    public void insertText(String text) {
        taDest.append(text);

    }

    private  class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

            if(e.getSource() == btnLoadFile){

                onLoadFile();
            }
            if(e.getSource() == btnFindReplace){
                onFindReplace();
            }
        }
    }

    /**
     * Called when the "Load file" button is pressed.
     */
    private void onLoadFile()
    {
        JFileChooser chooser = new JFileChooser();
        File file = null;
        try
        {
            file = new File(new File(".").getCanonicalPath());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        chooser.setCurrentDirectory(file);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text", "txt");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
                taSource.setText(String.join("\n", lines));
                lblSource.setText("Source file: " + f.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * Called when the "Find and replace" button is pressed.
     */
    private void onFindReplace() {

        System.out.println(tfReplaceWith.getText() + " replacetext");
        controller.execute(taDest, taSource.getText().split("\n"), tfFind.getText(), tfReplaceWith.getText());
    }
}