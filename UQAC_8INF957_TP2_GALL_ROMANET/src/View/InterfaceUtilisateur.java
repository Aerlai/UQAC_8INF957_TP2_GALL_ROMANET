package View;

import Model.Nourriture;
import Model.Pigeon;
import Model.PigeonSquare;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceUtilisateur extends JFrame implements MouseListener, Runnable {

    int tailleX;
    int tailleY;
    PigeonSquare pigeonsquare;


    // swing
    JComponent jc;

    public InterfaceUtilisateur(PigeonSquare ps){
        pigeonsquare = ps;
        jc = new Square(pigeonsquare);
        tailleX = ps.getTailleX();
        tailleY = ps.getTailleY();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PigeonSquare");
        this.setSize(tailleX+100, tailleY+100);
        setContentPane(jc);
        addMouseListener(this);
        this.setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            jc.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("test " + e.getX() + " " + e.getY());
        pigeonsquare.ajouterNourriture(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

