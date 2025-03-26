package klaseak;

public class Bezeroa extends pertsona {
    private int idBezeroa;
    private String izena;
    private String abizena1;
    private String abizena2;
    private String nan;
    private String email;
    private String pasahitza;

    public Bezeroa(int idBezeroa, String izena, String abizena1, String abizena2, String nan, String email, String pasahitza) {
        super(izena, abizena1, abizena2, nan, email, pasahitza);
        this.idBezeroa = idBezeroa;
        this.izena = izena;
        this.abizena1 = abizena1;
        this.abizena2 = abizena2;
        this.nan = nan;
        this.email = email;
        this.pasahitza = pasahitza;
    }

    public int getIdBezeroa() {
        return idBezeroa;
    }

    public void setIdBezeroa(int idBezeroa) {
        this.idBezeroa = idBezeroa;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena1() {
        return abizena1;
    }

    public void setAbizena1(String abizena1) {
        this.abizena1 = abizena1;
    }

    public String getAbizena2() {
        return abizena2;
    }

    public void setAbizena2(String abizena2) {
        this.abizena2 = abizena2;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }
}
