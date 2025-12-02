package Modelo;

public class Pokemon {
    private int id;
    private String especie;
    private String objeto;
    private String naturaleza;
    private String habilidad;
    private String genero;
    private String movimiento1;
    private String movimiento2;
    private String movimiento3;
    private String movimiento4;
    private int ivVida, ivAtaque, ivAtaqueEspecial, ivDefensa, ivDefensaEspecial, ivVelocidad;
    private int evVida, evAtaque, evAtaqueEspecial, evDefensa, evDefensaEspecial, evVelocidad;
    private String descripcion;

    public Pokemon(int id, String especie, String objeto, String naturaleza, String habilidad, String genero,
                   String movimiento1, String movimiento2, String movimiento3, String movimiento4,
                   int ivVida, int ivAtaque, int ivAtaqueEspecial, int ivDefensa, int ivDefensaEspecial, int ivVelocidad,
                   int evVida, int evAtaque, int evAtaqueEspecial, int evDefensa, int evDefensaEspecial, int evVelocidad,
                   String descripcion) {
        this.id = id;
        this.especie = especie;
        this.objeto = objeto;
        this.naturaleza = naturaleza;
        this.habilidad = habilidad;
        this.genero = genero;
        this.movimiento1 = movimiento1;
        this.movimiento2 = movimiento2;
        this.movimiento3 = movimiento3;
        this.movimiento4 = movimiento4;
        this.ivVida = ivVida;
        this.ivAtaque = ivAtaque;
        this.ivAtaqueEspecial = ivAtaqueEspecial;
        this.ivDefensa = ivDefensa;
        this.ivDefensaEspecial = ivDefensaEspecial;
        this.ivVelocidad = ivVelocidad;
        this.evVida = evVida;
        this.evAtaque = evAtaque;
        this.evAtaqueEspecial = evAtaqueEspecial;
        this.evDefensa = evDefensa;
        this.evDefensaEspecial = evDefensaEspecial;
        this.evVelocidad = evVelocidad;
        this.descripcion = descripcion;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getMovimiento1() {
		return movimiento1;
	}

	public void setMovimiento1(String movimiento1) {
		this.movimiento1 = movimiento1;
	}

	public String getMovimiento2() {
		return movimiento2;
	}

	public void setMovimiento2(String movimiento2) {
		this.movimiento2 = movimiento2;
	}

	public String getMovimiento3() {
		return movimiento3;
	}

	public void setMovimiento3(String movimiento3) {
		this.movimiento3 = movimiento3;
	}

	public String getMovimiento4() {
		return movimiento4;
	}

	public void setMovimiento4(String movimiento4) {
		this.movimiento4 = movimiento4;
	}

	public int getIvVida() {
		return ivVida;
	}

	public void setIvVida(int ivVida) {
		this.ivVida = ivVida;
	}

	public int getIvAtaque() {
		return ivAtaque;
	}

	public void setIvAtaque(int ivAtaque) {
		this.ivAtaque = ivAtaque;
	}

	public int getIvAtaqueEspecial() {
		return ivAtaqueEspecial;
	}

	public void setIvAtaqueEspecial(int ivAtaqueEspecial) {
		this.ivAtaqueEspecial = ivAtaqueEspecial;
	}

	public int getIvDefensa() {
		return ivDefensa;
	}

	public void setIvDefensa(int ivDefensa) {
		this.ivDefensa = ivDefensa;
	}

	public int getIvDefensaEspecial() {
		return ivDefensaEspecial;
	}

	public void setIvDefensaEspecial(int ivDefensaEspecial) {
		this.ivDefensaEspecial = ivDefensaEspecial;
	}

	public int getIvVelocidad() {
		return ivVelocidad;
	}

	public void setIvVelocidad(int ivVelocidad) {
		this.ivVelocidad = ivVelocidad;
	}

	public int getEvVida() {
		return evVida;
	}

	public void setEvVida(int evVida) {
		this.evVida = evVida;
	}

	public int getEvAtaque() {
		return evAtaque;
	}

	public void setEvAtaque(int evAtaque) {
		this.evAtaque = evAtaque;
	}

	public int getEvAtaqueEspecial() {
		return evAtaqueEspecial;
	}

	public void setEvAtaqueEspecial(int evAtaqueEspecial) {
		this.evAtaqueEspecial = evAtaqueEspecial;
	}

	public int getEvDefensa() {
		return evDefensa;
	}

	public void setEvDefensa(int evDefensa) {
		this.evDefensa = evDefensa;
	}

	public int getEvDefensaEspecial() {
		return evDefensaEspecial;
	}

	public void setEvDefensaEspecial(int evDefensaEspecial) {
		this.evDefensaEspecial = evDefensaEspecial;
	}

	public int getEvVelocidad() {
		return evVelocidad;
	}

	public void setEvVelocidad(int evVelocidad) {
		this.evVelocidad = evVelocidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
}
