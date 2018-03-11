import Model.PigeonSquare;
import View.InterfaceUtilisateur;

public class Application {
    public static void main(String [] args){
        PigeonSquare ps = new PigeonSquare();
        ps.start();
        InterfaceUtilisateur ui = new InterfaceUtilisateur(ps);
        Thread uiThread = new Thread(ui);
        uiThread.start();
    }
}
