import javax.swing.*;

public class NatureTerrain implements Element{
    private Character representation;
    private String colorCode;
    private String name;
    private ImageIcon representationGraphique = new ImageIcon("./ressources/MissingTexture.png");

    public NatureTerrain(String name, Character representation, String colorCode, String pathImage) {
        this.representation = representation;
        this.colorCode = colorCode;
        this.name =name;
        if (pathImage != null){
            representationGraphique = new ImageIcon("./ressources/"+pathImage);
        }
    }

    public String getName() {
        return name;
    }
    @Override
    public Character getRepresentationConsole() {
        return representation;
    }

    @Override
    public boolean doitEtreAffiche(Partie p) {
        return true;
    }

    @Override
    public final ImageIcon getRepresentationGraphique(int turn) {
        return representationGraphique;
    }

    // un ANSI escape codes, permettant de colorer le fond de la case si la console utilisé le permet,
    // ou Null si non défini
    public String getColorCode() {
        return colorCode;
    }
}
