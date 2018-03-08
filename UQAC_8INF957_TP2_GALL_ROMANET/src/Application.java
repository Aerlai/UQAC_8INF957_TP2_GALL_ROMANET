import Model.PigeonSquare;
import View.InterfaceUtilisateur;

public class Application {
    public static void main(String [] args){
        PigeonSquare ps = new PigeonSquare();
        InterfaceUtilisateur ui = new InterfaceUtilisateur(ps);
    }
}
