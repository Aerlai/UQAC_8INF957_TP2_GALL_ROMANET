import javax.swing.*;

public class PigeonSquare extends JFrame {
    // Attributs
    private int tailleX = 500;
    private int tailleY = 500;

    // variables interface
    private JPanel container = new JPanel();

    // Getters

    // Setter

    // Methode

    public PigeonSquare(){
        // Creation des pigeons
        Pigeon p1 = new Pigeon(10, 10);

        // Création des threads des pigeons
        Thread t1 = new Thread(p1);

        // Initialisation du canvas
        initUi();



    }

    private void initUi(){
        this.setSize(this.tailleX, this.tailleY);
        this.setTitle("Pigeon Square");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //On initialise le conteneur avec tous les composants
        initComposant();
        //On ajoute le conteneur
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void initComposant() {
    }

}
