package Modelo;

public class Genero {
    private String genero;
    private String especie;

    public Genero(String genero, String especie) {
        this.genero = genero;
        this.especie = especie;
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
}
