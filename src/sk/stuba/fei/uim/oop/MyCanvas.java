package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyCanvas extends JPanel implements MouseMotionListener, MouseListener {
    private final MyJPanel panel;
    private final ArrayList<MyTree> myObjects = new ArrayList<>();
    private MyTree currentTree;
    private boolean clickedOnObject = false;
    private int clickedObjectIndex;
    private int posx;
    private int posy;
    private int differenceX;
    private int differenceY;

    public MyCanvas(MyJPanel panel) {
        super();
        this.panel = panel;
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MyTree s : myObjects) {
            s.paintTree(g);
        }
        if (currentTree != null) {
            currentTree.paintTree(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        posx = e.getX();
        posy = e.getY();
        if (panel.isTreeClicked()) {
            currentTree = new MyTree(posx, posy, 1, 1, panel.getC());
        }
        if (panel.isMoveClicked()) {
            int counter = 0;
            for (MyTree s : myObjects) {
                if (s.contains(e.getX(), e.getY())) {
                    clickedOnObject = true;
                    clickedObjectIndex = counter;
                    differenceX = posx - s.x;
                    differenceY = posy - s.y;
                }
                counter++;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (panel.isMoveClicked()) {
            clickedOnObject = false;
            currentTree = null;
        }
        if (panel.isTreeClicked()) {
            myObjects.add(currentTree);
            repaint();
            currentTree = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX();
        int dy = e.getY();
        if (panel.isMoveClicked() && clickedOnObject) {
            currentTree = myObjects.get(clickedObjectIndex);
            currentTree.x = dx - differenceX;
            currentTree.y = dy - differenceY;
            repaint();
        }
        if (panel.isTreeClicked()) {
            if (currentTree != null) {
                if (dx > posx && dy > posy) {
                    currentTree.width = dx - posx;
                    currentTree.height = dy - posy;
                }

                if (dx < posx && dy < posy) {
                    currentTree.x = dx;
                    currentTree.y = dy;
                    currentTree.width = posx - dx;
                    currentTree.height = posy - dy;
                }
                if (dx > posx && dy < posy) {
                    currentTree.y = dy;
                    currentTree.width = dx - posx;
                    currentTree.height = posy - dy;
                }
                if (dx < posx && dy > posy) {
                    currentTree.x = dx;
                    currentTree.width = posx - dx;
                    currentTree.height = dy - posy;
                }
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
