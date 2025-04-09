public class Nodo<K,V> {
    protected K clave;
    protected V valor;
    protected Nodo<K, V> menor;
    protected Nodo<K, V> mayor;
    int altura;

    public Nodo(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
        menor = mayor = null;
        altura = 1;
    }

    public K getClave() {
        return clave;
    }
    public V getValor() {
        return valor;
    }
    public Nodo<K, V> getMenor() {
        return menor;
    }
    public void setMenor(){
        menor = this;
    }
    public Nodo<K, V> getMayor() {
        return mayor;
    }
    public void setMayor(){
        mayor = this;
    }
}
