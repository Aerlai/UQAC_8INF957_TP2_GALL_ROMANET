package Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PigeonSquare extends Observable implements Observer {
    // Attributs
    private int nombreDePigeon = 5;
    private int tailleX = 500; // taille du square en X
    private int tailleY = 500; // taille du square en Y
    private ArrayList<Nourriture> nourritureTab = new ArrayList();
    private ArrayList<Pigeon> pigeonTab = new ArrayList();
    private ArrayList<Thread> threadTab = new ArrayList();
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    // Getters

    // Setter

    // Constructeur
    public PigeonSquare() {

        creerPigeonThread(nombreDePigeon);

        // Gestion de la duree de ve de la nourriture
        executor.submit(() -> {
            while (true) {

                if (nourritureTab.size() > 0) {
                    for (int i = 0; i < nourritureTab.size(); i++) {
                        nourritureTab.get(i).temps();
                        this.setChanged();
                        if (nourritureTab.get(i).getDureDeVie() == -5) {
                            nourritureTab.remove(i);
                        }
                    }

                }
                this.notifyObservers(this);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getTailleX() {
        return tailleX;
    }

    public void setTailleX(int tailleX) {
        this.tailleX = tailleX;
    }

    public int getTailleY() {
        return tailleY;
    }

    public void setTailleY(int tailleY) {
        this.tailleY = tailleY;
    }

    public int getNombreDePigeon() {
        return nombreDePigeon;
    }

    public ArrayList<Nourriture> getNourritureTab() {
        return nourritureTab;
    }

    public ArrayList<Pigeon> getPigeonTab() {
        return pigeonTab;
    }

    private void creerPigeonThread(int nbrePigeon) {
        Random ran = new Random();
        for (int i = 0; i < nbrePigeon; i++) {
            int x = ran.nextInt(tailleX) + 0;
            int y = ran.nextInt(tailleY) + 0;
            pigeonTab.add(new Pigeon(x, y, i, this));
            pigeonTab.get(i).addObserver(this);
            // 2 façon de créer des threads soit par java.util.thread soit par l'executor
            threadTab.add(new Thread(pigeonTab.get(i)));
            threadTab.get(i).start();
            //executor.execute(pigeonTab.get(i));
            this.notifyObservers(this);

        }
    }

    public void start() {
        executor.submit(() -> {
            ajouterNourriture(0, 0);
        });
        executor.submit(() -> {
            ajouterNourriture(500, 500, 8);
        });
    }

    // Methode
    // Ajouter une nourriture avec une durée de vie de 3 secondes
    public void ajouterNourriture(int x, int y) {
        nourritureTab.add(new Nourriture(x, y));
        int n = nourritureTab.size();
        this.setChanged();
        this.notifyObservers(this);

    }

    // Ajouter une nourriture avec une durée de vie précise
    public void ajouterNourriture(int x, int y, int vie) {
        nourritureTab.add(new Nourriture(x, y, vie));
        int n = nourritureTab.size();
        this.setChanged();
        this.notifyObservers(this);
    }

    // Observer
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Pigeon) {
            executor.submit(() -> {
                Pigeon p = (Pigeon) arg;
                //System.out.println("Model.Pigeon " + p.getId() + " ; posX : " + p.getPosX() + " ; posY : " + p.getPosY());
                if(p.getAMange() == true){
                    nourritureTab.remove(p.getNumNourriture());
                    this.setChanged();
                }
            });
        }
        this.notifyObservers(this);

    }
}
