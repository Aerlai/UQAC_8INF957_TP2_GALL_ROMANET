import java.util.ArrayList;
import java.util.Observable;

public class Pigeon extends Observable implements Runnable {
    // Attributs
    private int posX;
    private int posY;
    private int id;

    private ArrayList argObservateur;// données evenementiel

    // Constructeur
    Pigeon(int x, int y, int id){
        this.posX = x;
        this.posY = y;
        this.id = id;
    }

    // getters

    // Setters

    // Methodes
    @Override
    public void run() {
        System.out.println("Création d'un thread pour pigeon " + id);
        ArrayList v = new ArrayList();
        v.add(posX);
        v.add(posY);
        v.add(id);
        this.setChanged();
        this.notifyObservers(v);
        //System.out.println("Notif envoyé");
    }

    // Observer
}
