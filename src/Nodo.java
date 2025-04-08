public class Nodo<K,V> {
    protected K key;
    private V valor;
    protected Nodo<K, V> menor;
    protected Nodo<K, V> mayor;

    public Nodo(K key, V valor) {
        this.key = key;
        this.valor = valor;
        menor = mayor = null;
    }

    public
}
