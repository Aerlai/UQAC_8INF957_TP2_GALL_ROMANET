import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class PigeonSquare extends Observable implements Observer {
    // Attributs
    private int tailleX = 500; // taille du square en X
    private int tailleY = 500; // taille du square en Y
    private ArrayList<Nourriture> nourritureTab = new ArrayList();
    private ArrayList<Pigeon> pigeonTab = new ArrayList();
    private ArrayList<Thread> threadTab = new ArrayList();

    // Getters

    // Setter

    // Constructeur
    public PigeonSquare(){
        creerPigeonThread(1);


    }

    private void creerPigeonThread(int nbrePigeon){
        Random ran = new Random();
        for(int i = 0 ; i < nbrePigeon; i++){
            int x = ran.nextInt(tailleX) + 0;
            int y = ran.nextInt(tailleY) + 0;
            pigeonTab.add(new Pigeon(x,y,i,this));
            pigeonTab.get(i).addObserver(this);
            threadTab.add(new Thread(pigeonTab.get(i)));
            threadTab.get(i).start();
        }
    }

    public void start(){
        ajouterNourriture(100, 100);
    }

    // Methode
    public void ajouterNourriture(int x, int y){
        nourritureTab.add(new Nourriture(x,y));
        this.setChanged();
        this.notifyObservers(nourritureTab.get(nourritureTab.size()-1));
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
