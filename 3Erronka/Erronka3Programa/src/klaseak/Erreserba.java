package klaseak;

public class Erreserba {
    private int id;
    private int idBezeroa;
    private int idOrdutegi;
    private int kopurua;
    private String egoera;

    public Erreserba(int id, int idBezeroa, int idOrdutegi, int kopurua, String egoera) {
        this.id = id;
        this.idBezeroa = idBezeroa;
        this.idOrdutegi = idOrdutegi;
        this.kopurua = kopurua;
        this.egoera = egoera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBezeroa() {
        return idBezeroa;
    }

    public void setIdBezeroa(int idBezeroa) {
        this.idBezeroa = idBezeroa;
    }

    public int getIdOrdutegi() {
        return idOrdutegi;
    }

    public void setIdOrdutegi(int idOrdutegi) {
        this.idOrdutegi = idOrdutegi;
    }

    public int getKopurua() {
        return kopurua;
    }

    public void setKopurua(int kopurua) {
        this.kopurua = kopurua;
    }

    public String getEgoera() {
        return egoera;
    }

    public void setEgoera(String egoera) {
        this.egoera = egoera;
    }

	public Erreserba(int id, int idBezeroa, int idOrdutegi, int kopurua) {
		super();
		this.id = id;
		this.idBezeroa = idBezeroa;
		this.idOrdutegi = idOrdutegi;
		this.kopurua = kopurua;
	}
}
