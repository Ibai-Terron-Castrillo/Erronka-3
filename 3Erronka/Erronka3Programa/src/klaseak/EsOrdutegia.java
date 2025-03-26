package klaseak;

public class EsOrdutegia {
    private int id;
    private int idEserlekua;
    private int idOrdutegi;
    private boolean beteta;

    public EsOrdutegia(int id, int idEserlekua, int idOrdutegi, boolean beteta) {
        this.id = id;
        this.idEserlekua = idEserlekua;
        this.idOrdutegi = idOrdutegi;
        this.beteta = beteta;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEserlekua() {
        return idEserlekua;
    }

    public void setIdEserlekua(int idEserlekua) {
        this.idEserlekua = idEserlekua;
    }

    public int getIdOrdutegi() {
        return idOrdutegi;
    }

    public void setIdOrdutegi(int idOrdutegi) {
        this.idOrdutegi = idOrdutegi;
    }

    public boolean isBeteta() {
        return beteta;
    }

    public void setBeteta(boolean beteta) {
        this.beteta = beteta;
    }


	public EsOrdutegia(int idEserlekua, int idOrdutegi, boolean beteta) {
		super();
		this.idEserlekua = idEserlekua;
		this.idOrdutegi = idOrdutegi;
		this.beteta = beteta;
	}
}
