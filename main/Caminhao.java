package main;

public class Caminhao{
	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Caminhao(String placa) {
		super();
		this.placa = placa;
	}

	@Override
	public String toString() {
	      return placa;
	}
}
