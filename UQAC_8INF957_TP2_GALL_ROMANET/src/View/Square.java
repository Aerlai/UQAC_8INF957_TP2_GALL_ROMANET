package View;

import Model.Nourriture;
import Model.Pigeon;
import Model.PigeonSquare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Square extends JPanel {
    private PigeonSquare ps;

    // Constructeur
    public Square(PigeonSquare ps){
        this.ps = ps;
    }

    // Methodes

    // On dessine les composants dans le Jpanel
    public void paintComponent(Graphics g){
        nettoyer(g);
        dessinerLesPigeons(g);
        dessinerNourritures(g);
    }

    // Fonction qui permet d'effacer le Jpanel entre deux images
    public void nettoyer(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    // On va chercher la position des pigeons puis on les dessine
    public void dessinerLesPigeons(Graphics g){
        ArrayList<Pigeon> pigeons = ps.getPigeonTab();
        for (int i=0; i<pigeons.size(); i++){
            g.setColor(Color.red);
            g.fillOval(pigeons.get(i).getPosX(), pigeons.get(i).getPosY(), 10, 10);
        }
    }

    // On va chercher la position des nourritures puis on les dessine
    public void dessinerNourritures(Graphics g){
        ArrayList<Nourriture> nourritures = ps.getNourritureTab();
        for (int i=0; i<nourritures.size(); i++){
            // Si la nourriture est mauvaise on la met en orange
            if (nourritures.get(i).isGate() == true){
                g.setColor(Color.orange);
                g.fillOval(nourritures.get(i).getPosX(), nourritures.get(i).getPosY(), 10, 10);
            }
            // si la nourriture est bonne on la met verte
            if (nourritures.get(i).isGate() == false){
            g.setColor(Color.green);
            g.fillOval(nourritures.get(i).getPosX(), nourritures.get(i).getPosY(), 10, 10);
            }
        }
    }
}
