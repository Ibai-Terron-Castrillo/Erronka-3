package klaseak;

public class Eserlekua {
    private int id;
    private int idAreto;
    private int zenbakia;
    private boolean beteta;

    public Eserlekua(int id, int idAreto, int zenbakia, boolean beteta) {
        this.id = id;
        this.idAreto = idAreto;
        this.zenbakia = zenbakia;
        this.beteta = beteta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAreto() {
        return idAreto;
    }

    public void setIdAreto(int idAreto) {
        this.idAreto = idAreto;
    }

    public int getZenbakia() {
        return zenbakia;
    }

    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    public boolean isBeteta() {
        return beteta;
    }

    public void setBeteta(boolean beteta) {
        this.beteta = beteta;
    }
}
