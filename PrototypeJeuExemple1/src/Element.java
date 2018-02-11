import javax.swing.*;

public interface Element {
    // Un caractère qui représente l'élement pur l'affichage console.
    Character getRepresentationConsole();

    boolean doitEtreAffiche(Partie p);

    ImageIcon getRepresentationGraphique(int turn);
}
