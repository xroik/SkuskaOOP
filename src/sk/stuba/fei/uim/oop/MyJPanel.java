package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJPanel extends JPanel implements ActionListener {
    private final JButton createTree;
    private final JButton moveTree;
    private final JButton color;
    private final JLabel text;
    private Color c = Color.RED;
    private  boolean treeClicked = true;
    private  boolean moveClicked = false;
    private int counter = 1;

    public boolean isTreeClicked() {
        return treeClicked;
    }
    public boolean isMoveClicked(){
        return moveClicked;
    }
    public Color getC(){
        return c;
    }

    public MyJPanel(){
        createTree = new JButton("Create a tree");
        moveTree = new JButton("Move a tree");
        color = new JButton("Next color");
        text = new JLabel("Status",SwingConstants.CENTER);
        text.setOpaque(true);
        text.setBackground(c);
        this.setLayout(new GridLayout(1,4));
        createTree.addActionListener(this);
        moveTree.addActionListener(this);
        color.addActionListener(this);
        this.add(createTree);
        this.add(moveTree);
        this.add(color);
        this.add(text);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create a tree")) {
            treeClicked = true;
            moveClicked = false;
            text.setText("KRESLENIE");
        }
        if (e.getActionCommand().equals("Move a tree")) {
            treeClicked = false;
            moveClicked = true;
            text.setText("PRESÚVANIE");
        }
        if (e.getActionCommand().equals("Next color")) {
            switch (counter) {
                case 0:
                    c = Color.RED;
                    counter++;
                    break;
                case 1:
                    c = Color.BLUE;
                    counter++;
                    break;
                case 2:
                    c = Color.GREEN;
                    counter++;
                    break;
                case 3:
                    c = Color.YELLOW;
                    counter = 0;
                    break;
            }
            text.setBackground(c);
        }
    }
}
