import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PigeonSquare extends Observable implements Observer {
    // Attributs
    private int nombreDePigeon = 2;
    private int tailleX = 100; // taille du square en X
    private int tailleY = 100; // taille du square en Y
    private ArrayList<Nourriture> nourritureTab = new ArrayList();
    private ArrayList<Pigeon> pigeonTab = new ArrayList();
    private ArrayList<Thread> threadTab = new ArrayList();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    // Getters

    // Setter

    // Constructeur
    public PigeonSquare() {

        creerPigeonThread(nombreDePigeon);


    }

    private void creerPigeonThread(int nbrePigeon) {
        Random ran = new Random();
        for (int i = 0; i < nbrePigeon; i++) {
            int x = ran.nextInt(tailleX) + 0;
            int y = ran.nextInt(tailleY) + 0;
            pigeonTab.add(new Pigeon(x, y, i, this));
            pigeonTab.get(i).addObserver(this);
            threadTab.add(new Thread(pigeonTab.get(i)));
            threadTab.get(i).start();
            //executor.execute(pigeonTab.get(i));
        }
    }

    public void start() {
        executor.submit(() -> {
            ajouterNourriture(0, 0);
        });
        executor.submit(() -> {
            ajouterNourriture(100, 100);
        });
    }

    // Methode
    public void ajouterNourriture(int x, int y) {
        nourritureTab.add(new Nourriture(x, y));
        this.setChanged();
        this.notifyObservers(nourritureTab);
    }

    // Observer
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Pigeon) {
            executor.submit(() -> {
                Pigeon p = (Pigeon) arg;
                System.out.println("Pigeon " + p.getId() + " ; posX : " + p.getPosX() + " ; posY : " + p.getPosY());
                if(p.getAMange() == true){
                    nourritureTab.remove(p.getNumNourriture());
                    this.setChanged();
                    this.notifyObservers(nourritureTab);
                }
            });
        }
    }
}
