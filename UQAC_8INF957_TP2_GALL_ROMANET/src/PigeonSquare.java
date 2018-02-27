import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PigeonSquare extends Observable implements Observer {
    // Attributs
    private int tailleX = 500;
    private int tailleY = 500;

    // variables interface
    private JPanel container = new JPanel();

    // Getters

    // Setter

    // Methode

    public PigeonSquare(){
        // Creation des pigeons
        Pigeon p1 = new Pigeon(5, 100, 1);
        Pigeon p2 = new Pigeon(16, 20, 2);

        // Création des observers
        p1.addObserver(this);
        p2.addObserver(this);

        // Création des threads des pigeons
        Thread t1 = new Thread(p1);
        t1.start();
        Thread t2 = new Thread(p2);
        t2.start();
    }

    // Observer
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Pigeon){
            //System.out.println("notif recue");
            ArrayList v = (ArrayList)arg;
            System.out.println("Pigeon " + v.get(2) + " ; posX : " + v.get(0) + " ; posY : " + v.get(1) );
        }
    }
}
