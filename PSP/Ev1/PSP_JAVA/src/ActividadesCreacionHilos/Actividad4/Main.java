package ActividadesCreacionHilos.Actividad4;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		Semaphore semCajera1 = new Semaphore(1);
        Semaphore semCajera2 = new Semaphore(1);
        Semaphore semCajera3 = new Semaphore(1);
        
        ArrayList<Thread> clientesSem1 = new ArrayList<Thread>();
        ArrayList<Thread> clientesSem2 = new ArrayList<Thread>();
        ArrayList<Thread> clientesSem3 = new ArrayList<Thread>();

        final int REPETICIONES=8;

        for(int i=1;i<=REPETICIONES;i++){
        	Thread Cliente=new Thread(()->{
            	double longitudLista1 = clientesSem1.size();
            	double longitudLista2 = clientesSem2.size();
            	double longitudLista3 = clientesSem3.size();
            	
            	if (Math.min(longitudLista1, Math.min(longitudLista2, longitudLista3)) == longitudLista1) {
        			try {
        				clientesSem1.add(Thread.currentThread());
    					semCajera1.acquire();
    					System.out.println("Cobrando en el cajero 1");
    					Thread.sleep((long) (Math.random() * 3000 + 3000));
    					System.out.println("Finalizado cobrando en el cajero 1");
    					semCajera1.release();
    					clientesSem1.remove(Thread.currentThread());
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		} else if(Math.min(longitudLista1, Math.min(longitudLista2, longitudLista3)) == longitudLista2) {
        			try {
        				clientesSem2.add(Thread.currentThread());
    					semCajera2.acquire();
    					System.out.println("Cobrando en el cajero 2");
    					Thread.sleep((long) (Math.random() * 3000 + 3000));
    					System.out.println("Finalizado cobrando en el cajero 2");
    					semCajera2.release();
    					clientesSem2.remove(Thread.currentThread());
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		} else {
        			try {
        				clientesSem3.add(Thread.currentThread());
    					semCajera3.acquire();
    					System.out.println("Cobrando en el cajero 3");
    					Thread.sleep((long) (Math.random() * 3000 + 3000));
    					System.out.println("Finalizado cobrando en el cajero 3");
    					semCajera3.release();
    					clientesSem3.remove(Thread.currentThread());
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		}
            	
            });
        	Cliente.start();
        }
	}
}
