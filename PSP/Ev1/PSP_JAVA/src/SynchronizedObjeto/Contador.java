package SynchronizedObjeto;

class Contador {
    private int count = 0;

    public void incrementar() {
        synchronized(this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
