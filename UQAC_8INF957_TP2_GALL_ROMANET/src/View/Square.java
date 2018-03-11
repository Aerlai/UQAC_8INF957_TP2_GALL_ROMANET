package View;

import Model.Nourriture;
import Model.Pigeon;
import Model.PigeonSquare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Square extends JPanel {
    private PigeonSquare ps;

    public Square(PigeonSquare ps){
        this.ps = ps;
    }

    public void paintComponent(Graphics g){
        nettoyer(g);
        dessinerLesPigeons(g);
        dessinerNourritures(g);
    }

    public void nettoyer(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void dessinerLesPigeons(Graphics g){
        ArrayList<Pigeon> pigeons = ps.getPigeonTab();
        for (int i=0; i<pigeons.size(); i++){
            g.setColor(Color.red);
            g.fillOval(pigeons.get(i).getPosX(), pigeons.get(i).getPosY(), 10, 10);
        }
    }

    public void dessinerNourritures(Graphics g){
        ArrayList<Nourriture> nourritures = ps.getNourritureTab();
        for (int i=0; i<nourritures.size(); i++){
            g.setColor(Color.green);
            g.fillOval(nourritures.get(i).getPosX(), nourritures.get(i).getPosY(), 10, 10);
        }
    }
}
