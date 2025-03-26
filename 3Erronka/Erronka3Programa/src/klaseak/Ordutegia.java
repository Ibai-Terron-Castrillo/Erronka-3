package klaseak;

public class Ordutegia {
    private int id;
    private int idPelikula;
    private int idAreto;
    private String eguna;
    private String ordua;
    private boolean amaitua;

    public Ordutegia(int id, int idPelikula, int idAreto, String eguna, String ordua, boolean amaitua) {
        this.id = id;
        this.idPelikula = idPelikula;
        this.idAreto = idAreto;
        this.eguna = eguna;
        this.ordua = ordua;
        this.amaitua = amaitua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPelikula() {
        return idPelikula;
    }

    public void setIdPelikula(int idPelikula) {
        this.idPelikula = idPelikula;
    }

    public int getIdAreto() {
        return idAreto;
    }

    public void setIdAreto(int idAreto) {
        this.idAreto = idAreto;
    }

    public String getEguna() {
        return eguna;
    }

    public void setEguna(String eguna) {
        this.eguna = eguna;
    }

    public String getOrdua() {
        return ordua;
    }

    public void setOrdua(String ordua) {
        this.ordua = ordua;
    }

    public boolean isAmaitua() {
        return amaitua;
    }

    public void setAmaitua(boolean amaitua) {
        this.amaitua = amaitua;
    }
}
