package View;

import Model.Nourriture;
import Model.Pigeon;
import Model.PigeonSquare;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceUtilisateur extends JFrame implements Observer, ActionListener {

    int tailleX;
    int tailleY;
    PigeonSquare pigeonsquare;

    CaseGraphique[][] cases ;
    ArrayList<CaseGraphique> casesNonvvides;

    JMenuItem nouvellepartie;

    boolean fin; //indicateur si la simulation est terminée ou non


    public InterfaceUtilisateur(PigeonSquare ps){

        pigeonsquare=ps;
        pigeonsquare.addObserver(this);
        casesNonvvides = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PigeonSquare");
        this.setSize(1000, 700);
        tailleX = pigeonsquare.getTailleX();
        tailleY = pigeonsquare.getTailleY();

        JMenuBar barremenu = new JMenuBar();
        JMenu menu = new JMenu("Simulation");

        //la barre de menu avec les éléments ci dessous n'est pas encore fonctionnelle
        nouvellepartie = new JMenuItem("Nouvelle Partie");
        nouvellepartie.addActionListener(this);
        menu.add(nouvellepartie);
        barremenu.add(menu);
        setJMenuBar(barremenu);

        JComponent jc = new JPanel (new GridLayout(pigeonsquare.getTailleY(), pigeonsquare.getTailleX()));



        Border limitecase = BorderFactory.createLineBorder(Color.black,0);
        Border limitegrille = BorderFactory.createLineBorder(Color.black,5);


        cases = new CaseGraphique[tailleX][tailleY];


        for(int i = 0; i < tailleX; i++){
            for(int j = 0; j< tailleY; j++){
                CaseGraphique cg = new CaseGraphique(i,j, this);
                cases[i][j]=cg;
                cg.setBorder(limitecase);
                jc.add(cg);
            }
        }
        jc.setBorder(limitegrille);
        add(jc);
        setContentPane(jc);

        this.setVisible(true);

        pigeonsquare.start();

        fin=false;
        while(!fin){

        }
        fin();
        this.setVisible(false);
    }

    public void updatemap(PigeonSquare ps){

       /* for(View.CaseGraphique cg : casesNonvvides){
            paintCase(cg,0);
            cg.validate();
            cg.repaint();
            casesNonvvides.remove(cg);
        }*/
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                paintCase(cases[i][j],0);
                cases[i][j].validate();
                cases[i][j].repaint();
            }
        }
        for(Pigeon p : ps.getPigeonTab()){
            paintCase(cases[p.getPosX()][p.getPosY()],1);
            cases[p.getPosX()][p.getPosY()].validate();
            cases[p.getPosX()][p.getPosY()].repaint();
            //casesNonvvides.add(cases[p.getPosX()][p.getPosY()]);
        }
        for(Nourriture n : ps.getNourritureTab()){
            paintCase(cases[n.getPosX()][n.getPosY()],2);
            cases[n.getPosX()][n.getPosY()].validate();
            cases[n.getPosX()][n.getPosY()].repaint();
           // casesNonvvides.add(cases[n.getPosX()][n.getPosY()]);
        }
    }

    public void ajouterNourriture(){
    }

    @Override
    public void update(Observable o, Object arg){
        if (o instanceof PigeonSquare) {

            PigeonSquare ps = (PigeonSquare) arg;
            updatemap(ps);
        }
    }


    //Repeint une case en fonction de son contenu
    public void paintCase(CaseGraphique casegraphique, int id){
        switch (id){
            case 0:casegraphique.setBackground(Color.WHITE);break; //vide
            case 1:casegraphique.setBackground(Color.GRAY);break; //pigeon
            case 2:casegraphique.setBackground(Color.RED);break; //nourriture

            default:casegraphique.setBackground(Color.WHITE);break;
        }
    }


    public void fin(){  //Cette fonction affiche un message de fin du jeu
        String texte = "La simulation est terminée";
        JOptionPane.showInputDialog(texte);
    }


    //@Override
    public void actionPerformed(ActionEvent e) {
    }

}

