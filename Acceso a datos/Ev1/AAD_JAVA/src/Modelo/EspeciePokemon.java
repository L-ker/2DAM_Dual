package Modelo;

public class EspeciePokemon {
    private String especie;
    private int peso;
    private String tipo1;
    private String tipo2;
    private int vidaBase, ataqueBase, ataqueEspecialBase, defensaBase, defensaEspecialBase, velocidadBase;
    private String genero;

    public EspeciePokemon(String especie, int peso, String tipo1, String tipo2,
                          int vidaBase, int ataqueBase, int ataqueEspecialBase,
                          int defensaBase, int defensaEspecialBase, int velocidadBase,
                          String genero) {
        this.especie = especie;
        this.peso = peso;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.vidaBase = vidaBase;
        this.ataqueBase = ataqueBase;
        this.ataqueEspecialBase = ataqueEspecialBase;
        this.defensaBase = defensaBase;
        this.defensaEspecialBase = defensaEspecialBase;
        this.velocidadBase = velocidadBase;
        this.genero = genero;
    }

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getTipo1() {
		return tipo1;
	}

	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	public int getVidaBase() {
		return vidaBase;
	}

	public void setVidaBase(int vidaBase) {
		this.vidaBase = vidaBase;
	}

	public int getAtaqueBase() {
		return ataqueBase;
	}

	public void setAtaqueBase(int ataqueBase) {
		this.ataqueBase = ataqueBase;
	}

	public int getAtaqueEspecialBase() {
		return ataqueEspecialBase;
	}

	public void setAtaqueEspecialBase(int ataqueEspecialBase) {
		this.ataqueEspecialBase = ataqueEspecialBase;
	}

	public int getDefensaBase() {
		return defensaBase;
	}

	public void setDefensaBase(int defensaBase) {
		this.defensaBase = defensaBase;
	}

	public int getDefensaEspecialBase() {
		return defensaEspecialBase;
	}

	public void setDefensaEspecialBase(int defensaEspecialBase) {
		this.defensaEspecialBase = defensaEspecialBase;
	}

	public int getVelocidadBase() {
		return velocidadBase;
	}

	public void setVelocidadBase(int velocidadBase) {
		this.velocidadBase = velocidadBase;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

    
}
