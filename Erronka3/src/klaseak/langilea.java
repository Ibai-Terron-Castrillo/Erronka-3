package klaseak;

public class langilea extends pertsona {
    private int Id;
    private String Helbidea;
    private boolean Admin;
    private String Erabiltzailea;
    private String telefonoa;

    
    public langilea(int Id, String Izena, String abizena1, String abizena2, String Erabiltzailea, String Pasahitza, String email, String telefonoa, String Nan, String Helbidea, boolean Admin) {
        super(Izena, abizena1, abizena2, Pasahitza, email, Nan);  
        this.Id = Id;
        this.Helbidea = Helbidea;
        this.Admin = Admin;
        this.Erabiltzailea = Erabiltzailea;
        this.telefonoa = telefonoa;
    }

    public langilea() {
    }

    
    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        this.Admin = admin;
    }

    public int getIdLangilea() {
        return Id;
    }

    public void setIdLangilea(int idLangilea) {
        this.Id = idLangilea;
    }

    public String getHelbidea() {
        return Helbidea;
    }

    public void setHelbidea(String helbidea) {
        this.Helbidea = helbidea;
    }

    public String getErabiltzailea() {
        return Erabiltzailea;
    }

    public void setErabiltzailea(String erabiltzailea) {
        this.Erabiltzailea = erabiltzailea;
    }

    public String getTelefonoa() {
        return telefonoa;
    }

    public void setTelefonoa(String telefonoa) {
        this.telefonoa = telefonoa;
    }

	

	public langilea(String izena, String abizena1, String abizena2, String pasahitza, String email, String nan,
			String helbidea, boolean admin, String erabiltzailea, String telefonoa) {
		super(izena, abizena1, abizena2, pasahitza, email, nan);
		Helbidea = helbidea;
		Admin = admin;
		Erabiltzailea = erabiltzailea;
		this.telefonoa = telefonoa;
	}
}




   

    

