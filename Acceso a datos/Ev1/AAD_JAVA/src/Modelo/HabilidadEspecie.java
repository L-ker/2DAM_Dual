package Modelo;

public class HabilidadEspecie {
    private String habilidad;
    private String especie;

    public HabilidadEspecie(String habilidad, String especie) {
        this.habilidad = habilidad;
        this.especie = especie;
    }

    public String getHabilidad() { return habilidad; }
    public void setHabilidad(String habilidad) { this.habilidad = habilidad; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
}
