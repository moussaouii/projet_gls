
public class Case {
    private int x;
    private int y;
    private NatureTerrain natureTerrain;

    public Case(int x, int y, NatureTerrain natureTerrain) {
        this.x = x;
        this.y = y;
        this.natureTerrain = natureTerrain;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public NatureTerrain getNatureTerrain() {
        return natureTerrain;
    }
    
    public boolean equals(Case c) {
    	return this.x==c.x && this.y==c.y;
    }
}
