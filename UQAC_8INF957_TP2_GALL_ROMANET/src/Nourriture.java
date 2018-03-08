public class Nourriture {
    // Attributs
    private int posX; // position X sur le canvas
    private int posY; // position Y sur le canvas
    private boolean mange = false;
    private boolean gate = false;
    private int dureDeVie = 100; // Durée de vie de 3 secondes

    // Constructeur
    public Nourriture(int x, int y){
        this.posX = x;
        this.posY = y;
        System.out.println("nouvelle nourriture : "+this.posX +";"+this.posY);
    }

    public Nourriture(int x, int y, int vie){
        this.posX = x;
        this.posY = y;
        this.dureDeVie = vie;
        System.out.println("nouvelle nourriture : "+this.posX +";"+this.posY);
    }

    // Getters

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isGate() {
        return gate;
    }

    public int getDureDeVie() {
        return dureDeVie;
    }

    // Setter

    // Methode
    public void estMange(){
        mange = true;
        System.out.println("Nourriture mangée : " + posX + ";" + posY);
    }

    public void temps(){
        dureDeVie = dureDeVie - 1;
        if (dureDeVie <= 0 && gate == false){
            gate = true;
            System.out.println("La nourriture est gatée " + posX + ";" + posY);
        }
    }

}
