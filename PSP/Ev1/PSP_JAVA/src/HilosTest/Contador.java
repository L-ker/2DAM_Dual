package HilosTest;

public class Contador {

	private Integer contador;
	
	public Contador(int c) {
		this.contador = c;
	}
	
    public synchronized void incrementar(){
        contador++;
    }
    
    public String toString(){
        return "El valor del contador es" + contador;
    }
}
