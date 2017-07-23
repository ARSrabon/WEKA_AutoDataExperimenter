package data_experimenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Gui extends JFrame {

    private static Gui instance;

    public static Gui getInstance() {
        return instance;
    }

    public Gui() {
        setTitle("Auto Data Experimenter");
        setSize(700, 400);
        setResizable(false);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new Gui();
                instance.createView();
                instance.show();
            }
        });
    }

    private void createView() {
        getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(0, 75));
        getContentPane().add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    }
}
