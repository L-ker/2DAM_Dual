package RepasoHilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class pool {
	
    public static void main(String[] args) throws Exception {

        // -----------------------------------------------------------
        // 🔹 ¿QUÉ ES UN THREAD POOL?
        // Un "pool de hilos" es un conjunto fijo de hilos reutilizables.
        // En lugar de crear un hilo nuevo por cada tarea (caro y lento),
        // se crean unos pocos hilos que se van reutilizando.
        //
        // Ventajas:
        //   ✔ Menos consumo de recursos
        //   ✔ Mayor rendimiento
        //   ✔ Perfecto para ejecutar muchas tareas pequeñas
        //
        // Aquí creamos un pool con exactamente 3 hilos.
        // -----------------------------------------------------------
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // -----------------------------------------------------------
        // Enviamos 5 tareas al pool de hilos.
        // Como solo hay 3 hilos disponibles, el pool ejecuta 3 tareas,
        // y cuando una termina, el hilo se reutiliza para la siguiente.
        //
        // Es decir:
        //   - Nunca hay más de 3 tareas ejecutándose a la vez
        //   - Pero las 5 tareas se acabarán ejecutando
        // -----------------------------------------------------------
        for (int i = 1; i <= 5; i++) {
            int tareaId = i;  // Necesario porque usamos una lambda

            pool.execute(() -> {
                System.out.println("Tarea " + tareaId + 
                                   " ejecutada por " + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000); // Simula trabajo durante 1 segundo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Tarea " + tareaId + 
                                   " finalizada por " + Thread.currentThread().getName());
            });
        }

        // -----------------------------------------------------------
        // 🔹 pool.close() equivale a "shutdown()"
        // Significa: ya no aceptes más tareas, pero termina las que quedan.
        // -----------------------------------------------------------
        pool.close();

        // -----------------------------------------------------------
        // 🔹 Esperamos hasta 1 minuto a que terminen TODAS las tareas.
        //   - Si terminan → devuelve true
        //   - Si pasa 1 minuto y no acabaron → devuelve false (pero las
        //     tareas siguen ejecutándose en segundo plano)
        // -----------------------------------------------------------
        pool.awaitTermination(1, TimeUnit.MINUTES);
    }
}
