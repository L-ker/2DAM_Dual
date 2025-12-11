package RepasoHilos;

public class PropiedadesHilos {

	 public static void main(String[] args) throws Exception {
	        Thread[] hilos = new Thread[5];

	        // Crear los hilos con nombres personalizados
	        for (int i = 0; i < hilos.length; i++) {
	            int id = i; // necesario para lambda
	            hilos[i] = new Thread(() -> contar(), "Hilo_" + id);
	        }

	        // Estado inicial
	        for (Thread h : hilos) {
	            System.out.println(h.getName() + " estado inicial: " + h.getState());
	        }

	        // Iniciar los hilos
	        for (Thread h : hilos) h.start();
	        
	        // Monitorear estados hasta que todos terminen
	        boolean algunoVivo;
	        do {
	            algunoVivo = false;
	            System.out.println("==== Estados ====");
	            for (Thread h : hilos) {
	                System.out.println(h.getName() + ": " + h.getState());
	                if (h.isAlive()) algunoVivo = true;
	            }
	            Thread.sleep(1000);
	        } while (algunoVivo);

	        // Estados finales
	        System.out.println("\n=== Todos los hilos han terminado ===");
	        for (Thread h : hilos) {
	            System.out.println(h.getName() + " estado final: " + h.getState());
	        }
	    }

	    // Cada hilo cuenta con pausas
	    private static void contar() {
	        for (int i = 1; i <= 5; i++) {
	            System.out.println(Thread.currentThread().getName() + " cuenta " + i);
	            try { Thread.sleep(1000); } catch (InterruptedException e) {}
	        }
	    }
	    /*
	     * h.join() haria al main esperar hasta que acabe el hilo
	     */

	    /*
	     Otras formas de nombrar hilos:
	     - new Thread(runnable, "NombreHilo");
	     - thread.setName("NombreNuevo");
	     - extendiendo Thread y usando super("NombreHilo");
	    */
	}