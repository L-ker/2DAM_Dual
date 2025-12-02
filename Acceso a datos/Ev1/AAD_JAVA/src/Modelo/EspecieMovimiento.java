package Modelo;

public class EspecieMovimiento {
    private String especie;
    private String tipo;
    private String forma;
    private int potencia;
    private String movimiento;

    public EspecieMovimiento(String especie, String tipo, String forma, int potencia, String movimiento) {
        this.especie = especie;
        this.tipo = tipo;
        this.forma = forma;
        this.potencia = potencia;
        this.movimiento = movimiento;
    }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getForma() { return forma; }
    public void setForma(String forma) { this.forma = forma; }
    public int getPotencia() { return potencia; }
    public void setPotencia(int potencia) { this.potencia = potencia; }
    public String getMovimiento() { return movimiento; }
    public void setMovimiento(String movimiento) { this.movimiento = movimiento; }
}
