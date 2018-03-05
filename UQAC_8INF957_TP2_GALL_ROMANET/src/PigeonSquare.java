import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PigeonSquare extends Observable implements Observer {
    // Attributs
    private int tailleX = 500; // taille du square en X
    private int tailleY = 500; // taille du square en Y
    private ArrayList<Nourriture> nourritureTab = new ArrayList();

    // Getters

    // Setter

    // Constructeur
    public PigeonSquare(){
        // Pigeon 1
        Pigeon p1 = new Pigeon(0, 0, 1, this);
        p1.addObserver(this);
        Thread t1 = new Thread(p1);
        t1.start();


    }

    public void start(){
        ajouterNourriture(100, 100);
    }

    // Methode
    public void ajouterNourriture(int x, int y){
        Nourriture n = new Nourriture(x,y);
        nourritureTab.add(n);
        this.setChanged();
        this.notifyObservers(n);
    }

    // Observer
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Pigeon){
            Pigeon p = (Pigeon)arg;
            System.out.println("Pigeon " + p.getId() + " ; posX : " + p.getPosX()+ " ; posY : " + p.getPosY());
        }
    }
}
