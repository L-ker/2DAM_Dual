package HilosTest;


public class Hilo2 implements Runnable{

	private Contador c;
	private int TOTAL = 1000;
	
	public Hilo2(Contador c) {
		this.c = c;
	}
	
	@Override
	public void run() {
		for(int i=0; i < TOTAL; i++) {
			c.incrementar();
		}
	}
}
