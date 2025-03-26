package klaseak;

public class pertsona {
    private String Izena;
    private String abizena1;
    private String abizena2;
    private String Pasahitza;
    private String email;
    private String Nan;

    
    public pertsona(String izena, String abizena1, String abizena2, String pasahitza, String email, String nan) {
        this.Izena = izena;
        this.abizena1 = abizena1;
        this.abizena2 = abizena2;
        this.Pasahitza = pasahitza;
        this.email = email;
        this.Nan = nan;
    }

   
    public pertsona() {
    }

    
    public String getIzena() {
        return Izena;
    }

    public void setIzena(String izena) {
        this.Izena = izena;
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

    public String getPasahitza() {
        return Pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.Pasahitza = pasahitza;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNan() {
        return Nan;
    }

    public void setNan(String nan) {
        this.Nan = nan;
    }
}

