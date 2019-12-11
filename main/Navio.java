package main;

public class Navio{
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Navio(String matricula) {
		super();
		this.matricula = matricula;
	}

	@Override
	public String toString() {
	      return matricula;
	}
}
