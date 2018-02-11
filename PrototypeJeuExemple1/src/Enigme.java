
public class Enigme {
    public static void main(String[] argv) {
        boolean modeGraphique = argv.length == 0 || !argv[0].equals("-c");
        if (modeGraphique) {
            Jeu j1 = new Jeu_j1();
            GUInterface gui = new GUInterface(j1);
        } else {
            Jeu j1 = new Jeu_j1();
            j1.runFullJeu(null);
        }
    }
}
