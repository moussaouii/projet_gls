
public class Chemin extends Passage{
    private int energieNecessaireMobile;
    private int volumeMaxMobile;
    private int volumeCourantMobile;

    public Chemin(String name, Character representation, String colorCode, String pathImage, int energieNecessaireMobile, int volumeMaxMobile) {
        super(name, representation, colorCode, pathImage);
        this.energieNecessaireMobile = energieNecessaireMobile;
        this.volumeMaxMobile = volumeMaxMobile;
        volumeCourantMobile = 0;
    }

    public int getEnergieNecessaireMobile() {
        return energieNecessaireMobile;
    }

    public int getVolumeMaxMobile() {
        return volumeMaxMobile;
    }

    public boolean isVolumeAcceptabe(int addedVolume){
        return volumeMaxMobile >= volumeCourantMobile + addedVolume;
    }

    public void addVolume(int addedVolume) {
        volumeCourantMobile = volumeCourantMobile + addedVolume;
    }
}
