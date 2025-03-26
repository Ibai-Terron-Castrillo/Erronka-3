package klaseak;

public class Pelikula {
    private int id;
    private String izena;
    private int iraunaldia;
    private String generoa;
    private String sailkapena;
    private String sinopsia;
    private String aktoreak;
    private String zuzendaria;
    private String kartela;
    private String trailerra;

    public Pelikula(int id, String izena, int iraunaldia, String generoa, String sailkapena, String sinopsia, String aktoreak, String zuzendaria, String kartela, String trailerra) {
        this.id = id;
        this.izena = izena;
        this.iraunaldia = iraunaldia;
        this.generoa = generoa;
        this.sailkapena = sailkapena;
        this.sinopsia = sinopsia;
        this.aktoreak = aktoreak;
        this.zuzendaria = zuzendaria;
        this.kartela = kartela;
        this.trailerra = trailerra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public int getIraunaldia() {
        return iraunaldia;
    }

    public void setIraunaldia(int iraunaldia) {
        this.iraunaldia = iraunaldia;
    }

    public String getGeneroa() {
        return generoa;
    }

    public void setGeneroa(String generoa) {
        this.generoa = generoa;
    }

    public String getSailkapena() {
        return sailkapena;
    }

    public void setSailkapena(String sailkapena) {
        this.sailkapena = sailkapena;
    }

    public String getSinopsia() {
        return sinopsia;
    }

    public void setSinopsia(String sinopsia) {
        this.sinopsia = sinopsia;
    }

    public String getAktoreak() {
        return aktoreak;
    }

    public void setAktoreak(String aktoreak) {
        this.aktoreak = aktoreak;
    }

    public String getZuzendaria() {
        return zuzendaria;
    }

    public void setZuzendaria(String zuzendaria) {
        this.zuzendaria = zuzendaria;
    }

    public String getKartela() {
        return kartela;
    }

    public void setKartela(String kartela) {
        this.kartela = kartela;
    }

    public String getTrailerra() {
        return trailerra;
    }

    public void setTrailerra(String trailerra) {
        this.trailerra = trailerra;
    }
}
