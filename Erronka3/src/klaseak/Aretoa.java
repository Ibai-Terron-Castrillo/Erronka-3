package klaseak;

public class Aretoa {
    private int id;
    private String izena;
    private int edukiera;

    public Aretoa(int id, String izena, int edukiera) {
        this.id = id;
        this.izena = izena;
        this.edukiera = edukiera;
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

    public int getEdukiera() {
        return edukiera;
    }

    public void setEdukiera(int edukiera) {
        this.edukiera = edukiera;
    }
}
