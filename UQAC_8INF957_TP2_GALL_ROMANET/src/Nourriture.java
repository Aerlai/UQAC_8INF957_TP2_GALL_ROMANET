public class Nourriture {
    // Attributs
    private int posX; // position X sur le canvas
    private int posY; // position Y sur le canvas
    private double dureDeVie = 3.0; // Durée de vie de 3 secondes

    // Constructeur
    public Nourriture(int x, int y){
        this.posX = x;
        this.posY = y;
        System.out.println("nouvelle nourriture : "+this.posX +";"+this.posY);
    }

    // Getters

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    // Setter

    // Methode

}
