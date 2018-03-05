import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.lang.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pigeon extends Observable implements Runnable, Observer {
    // Attributs
    private int posX;
    private int posY;
    private int id;
    private ArrayList<Nourriture> nourritureTab = new ArrayList();
    private int vitesse = 100;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean rechercheActive = false;

    // Constructeur
    Pigeon(int x, int y, int id, PigeonSquare parent) {
        this.posX = x;
        this.posY = y;
        this.id = id;
        parent.addObserver(this);
    }

    // getters
    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getId() {
        return id;
    }

    // Setters

    // Methodes
    @Override
    public void run() {
        System.out.println("Création d'un thread pour pigeon " + id);
        this.setChanged();
        this.notifyObservers(this);
    }

    private void rechercherNourriture() {
        // On vérifie si il y a de la nourriture de disponible
        int xCible;
        int yCible;
        do {
            // Choix de la nouriture la plus proche
            int curseur = 0;
            double distance = 99999999; // On initialise à l'infini
            for (int i = 0; i < nourritureTab.size(); i++) {
                double v = calculDistance(this.posX, this.posY, nourritureTab.get(i).getPosX(), nourritureTab.get(i).getPosY()); // Calcul de la distance entre le pigeon et une nourriture
                if (v < distance) { // si la nourriture est plus proche on sauvegarde
                    curseur = i;
                    distance = v;
                }
            }

            // On dirige le pigeon vers la nourriture
            xCible = nourritureTab.get(curseur).getPosX();
            yCible = nourritureTab.get(curseur).getPosY();

                deplacerPigeonPas(xCible, yCible);
                this.setChanged();
                this.notifyObservers(this);
                try {
                    Thread.sleep(vitesse);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }while (this.posX != xCible || this.posY != yCible); // TODO : ajouter une condition nourriture gatée
            rechercheActive = false;
    }

    // Fonction de recherche du pigeon le plus proche.
    private double calculDistance(int xa, int ya, int xb, int yb) {
        return Math.sqrt((xb - xa) * (xb - xa) + (yb - ya) * (yb - ya));
    }

    // Méthode de déplacement du pigeon d'un pas.
    private void deplacerPigeonPas(int xCible, int yCible) {
        if (this.posX != xCible) {
            if (this.posX < xCible) this.posX++;
            else this.posX--;
        }
        if (this.posY != yCible) {
            if (this.posY < yCible) this.posY++;
            else this.posY--;
        }
    }

    // Observer
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PigeonSquare) {
            nourritureTab.add((Nourriture) arg);
            if(rechercheActive == false){
                rechercheActive = true;
                executor.submit(() -> {
                    rechercherNourriture();
                });
            }

        }

    }

}
