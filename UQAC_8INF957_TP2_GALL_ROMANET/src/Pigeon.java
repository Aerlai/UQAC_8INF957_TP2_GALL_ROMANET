public class Pigeon implements Runnable{
    // Attributs
    private int posX;
    private int posY;

    // Constructeur
    Pigeon(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    // getters

    // Setters

    // Methodes
    @Override
    public void run() {
        System.out.println("Création d'un thread pour pigeon");
    }

}
