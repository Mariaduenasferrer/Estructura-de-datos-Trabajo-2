import java.util.ArrayList;
import java.util.List;

import static java.awt.AWTEventMulticaster.add;

public class ArbolBinarioDeBusqueda<K,V> implements Comparable<K,V> {

    private Nodo<K,V> raiz;

    /*
    //Listas Generadas
    public getListaPreOrden(){
        return list;
    }
    public getListaPostOrden(){
        return list;
    }
    public getListaOrdenCentral(){
        return list;
    }
     */

    //Operaciones
    public void addNodoABB(K clave, V valor){
        if (Nodo == null) {
            return new Nodo<>(clave, valor);
        }
        if (clave.compareTo(Nodo.key) < 0) {
            Nodo.menor = add(Nodo.menor, clave, valor);
        } else if (clave.compareTo(Nodo.clave) > 0) {
            Nodo.menor = add(Nodo.mayor, clave, valor);
        }
        return Nodo;
    }

    //Subárboles
    public Lista<T> getSubArbolIzquierda(){
        return ArbolBinarioDeBusqueda;
    }
    public Lista<T> getSubArbolDerecha(){
        return ArbolBinarioDeBusqueda;
    }

    //Responder preguntas
    public int getGrado(Nodo<K, V> nodo) {
        if (nodo == null) {
            return 0;
        }else {
            return Math.max(getGrado(nodo.menor), getGrado(nodo.mayor)) + 1;
        }
    }

    public int getAltura() {
        return altura(raiz);
    }
    private int altura(Nodo<K, V> nodo) {
        if (nodo == null) return -1;
        return Math.max(altura(nodo.menor), altura(nodo.mayor)) + 1;
    }

    public Lista<V> getListaDatosNivel(int nivel) {
        Lista<V> list = new ArrayList<V>();
        getDatosNivel(raiz,nivel,0,list);
        return list;
    }
    private void getDatosNivel(Nodo<K, V> nodo, int nivel, int actual, List<V> lista) {
        if (nodo == null) return;
        if (actual == nivel) {
            lista.add(nodo.valor);
        } else {
            getDatosNivel(nodo.menor, nivel, actual + 1, lista);
            getDatosNivel(nodo.mayor, nivel, actual + 1, lista);
        }
    }

    public boolean isArbolHomogeneo() {
        return isHomogeneo(raiz);
    }

    private boolean isHomogeneo(Nodo<K, V> nodo) {
        if (nodo == null) return true;
        if (nodo.menor == null && nodo.menor == null) return true;
        if (nodo.mayor != null && nodo.mayor != null) {
            return isHomogeneo(nodo.menor) && isHomogeneo(nodo.mayor);
        }
        return false;
    }

    public boolean isArbolCompleto(){
        return boolean;
    }

    public boolean isArbolCasiCompleto(){
        return boolean;
    }

    public Lista<T> getCamino(){
        return list;
    }

    @Override
    public int compareTo(K o) {
        return 0;
    }
}
