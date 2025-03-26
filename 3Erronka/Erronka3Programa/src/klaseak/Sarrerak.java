package klaseak;

public class Sarrerak {
    private int id;
    private int idErreserba;
    private double prezioa;
    private int idEserleku;

    public Sarrerak(int id, int idErreserba, double prezioa, int idEserleku) {
        this.id = id;
        this.idErreserba = idErreserba;
        this.prezioa = prezioa;
        this.idEserleku = idEserleku;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdErreserba() {
        return idErreserba;
    }

    public void setIdErreserba(int idErreserba) {
        this.idErreserba = idErreserba;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public int getIdEserleku() {
        return idEserleku;
    }

    public void setIdEserleku(int idEserleku) {
        this.idEserleku = idEserleku;
    }
}