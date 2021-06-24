package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {

    public MyJFrame() {
        MyJPanel panel = new MyJPanel();
        MyCanvas canvas = new MyCanvas(panel);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.PAGE_END);
        this.add(canvas, BorderLayout.CENTER);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
}
