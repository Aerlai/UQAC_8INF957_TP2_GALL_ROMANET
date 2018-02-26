package Observer;

import java.util.Observable;

// Interface implémentée par tous les observateurs.
public interface Observateur
{
    // Méthode appelée automatiquement lorsque l'état change
    public void actualiser(Observable o);
}
