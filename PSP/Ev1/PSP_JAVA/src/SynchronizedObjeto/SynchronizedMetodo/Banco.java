package SynchronizedObjeto.SynchronizedMetodo;

class Banco {
    private int saldo = 1000;

    public synchronized void depositar(int cantidad) {
        saldo += cantidad;
    }

    public synchronized void retirar(int cantidad) {
        saldo -= cantidad;
    }
}
