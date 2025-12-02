package Modelo;

public class Equipo {
    private int id;
    private int idUsuario;
    private int idPokemon1, idPokemon2, idPokemon3, idPokemon4, idPokemon5, idPokemon6;
    private String descripcion;
    private int version;

    public Equipo(int id, int idUsuario, int idPokemon1, int idPokemon2, int idPokemon3, int idPokemon4, int idPokemon5, int idPokemon6,
                  String descripcion, int version) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPokemon1 = idPokemon1;
        this.idPokemon2 = idPokemon2;
        this.idPokemon3 = idPokemon3;
        this.idPokemon4 = idPokemon4;
        this.idPokemon5 = idPokemon5;
        this.idPokemon6 = idPokemon6;
        this.descripcion = descripcion;
        this.version = version;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPokemon1() {
		return idPokemon1;
	}

	public void setIdPokemon1(int idPokemon1) {
		this.idPokemon1 = idPokemon1;
	}

	public int getIdPokemon2() {
		return idPokemon2;
	}

	public void setIdPokemon2(int idPokemon2) {
		this.idPokemon2 = idPokemon2;
	}

	public int getIdPokemon3() {
		return idPokemon3;
	}

	public void setIdPokemon3(int idPokemon3) {
		this.idPokemon3 = idPokemon3;
	}

	public int getIdPokemon4() {
		return idPokemon4;
	}

	public void setIdPokemon4(int idPokemon4) {
		this.idPokemon4 = idPokemon4;
	}

	public int getIdPokemon5() {
		return idPokemon5;
	}

	public void setIdPokemon5(int idPokemon5) {
		this.idPokemon5 = idPokemon5;
	}

	public int getIdPokemon6() {
		return idPokemon6;
	}

	public void setIdPokemon6(int idPokemon6) {
		this.idPokemon6 = idPokemon6;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    
}
