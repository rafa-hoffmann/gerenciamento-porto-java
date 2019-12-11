package main;

public class Container {
	private String cod;
	private String loc;
	private String dataEntrada;
	private String endRemetente;

	private String remetente;
	private String destino;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getEndRemetente() {
		return endRemetente;
	}

	public void setEndRemetente(String endRemetente) {
		this.endRemetente = endRemetente;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Container(String cod, String loc, String dataEntrada, String endRemetente, String remetente,
			String destino) {
		super();
		this.cod = cod;
		this.loc = loc;
		this.dataEntrada = dataEntrada;
		this.endRemetente = endRemetente;
		this.remetente = remetente;
		this.destino = destino;
	}

}
