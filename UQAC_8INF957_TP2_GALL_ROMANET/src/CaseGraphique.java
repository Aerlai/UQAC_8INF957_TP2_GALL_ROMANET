
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CaseGraphique extends JPanel {

    int[] id;
    InterfaceUtilisateur interfaceUtilisateur;


    public CaseGraphique(int x, int y, InterfaceUtilisateur vue){
        super();
        this.id= new int[2];
        id[0]=x;
        id[1]=y;
        this.interfaceUtilisateur = vue;
        setBackground(Color.white);
        setLayout(new BorderLayout());


        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent arg0) {
                super.mouseClicked(arg0);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                super.mouseExited(arg0);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                System.out.println(id[0] +"   "+ id[1]);
            }
        });
    }

}
