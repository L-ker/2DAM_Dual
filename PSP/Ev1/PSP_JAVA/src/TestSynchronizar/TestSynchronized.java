package TestSynchronizar;

public class TestSynchronized {
    static class Contador {
        private int valor = 0;

        public void incrementar() {
            synchronized(this) { // solo un hilo a la vez puede entrar
                valor++;
                System.out.println(Thread.currentThread().getName() + " -> " + valor);
            }
        }
    }

    public static void main(String[] args) {
        Contador c = new Contador();

        Runnable r = () -> {
            for (int i = 0; i < 5; i++) c.incrementar();
        };

        Thread t1 = new Thread(r, "Hilo 1");
        Thread t2 = new Thread(r, "Hilo 2");

        t1.start();
        t2.start();
    }
}
