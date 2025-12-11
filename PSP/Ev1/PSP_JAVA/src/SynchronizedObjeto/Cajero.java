package SynchronizedObjeto;

class Cajero {
    private int dinero = 1000;
    private Object lock = new Object(); // objeto usado como candado

    public void retirar(int cantidad) {
        synchronized(lock) { // bloquea el objeto lock
            if (dinero >= cantidad) {
                dinero -= cantidad;
                System.out.println(Thread.currentThread().getName() + " retiró " + cantidad);
            }
        }
    }
}
