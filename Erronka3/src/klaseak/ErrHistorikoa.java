package klaseak;

import java.sql.Date;

public class ErrHistorikoa {
    private int id;
    private int idBezeroa;
    private int idOrdutegi;
    private int kopurua;
    private Date amaieraData;

    public ErrHistorikoa(int id, int idBezeroa, int idOrdutegi, int kopurua, Date amaieraData) {
        this.id = id;
        this.idBezeroa = idBezeroa;
        this.idOrdutegi = idOrdutegi;
        this.kopurua = kopurua;
        this.amaieraData = amaieraData;
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

    public Date getAmaieraData() {
        return amaieraData;
    }

    public void setAmaieraData(Date amaieraData) {
        this.amaieraData = amaieraData;
    }

	public ErrHistorikoa(int idBezeroa, int idOrdutegi, int kopurua, Date amaieraData) {
		super();
		this.idBezeroa = idBezeroa;
		this.idOrdutegi = idOrdutegi;
		this.kopurua = kopurua;
		this.amaieraData = amaieraData;
	}
}
