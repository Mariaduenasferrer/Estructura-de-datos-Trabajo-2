import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioDeBusqueda<K extends Comparable<K>,V> {

    protected Nodo<K,V> raiz;

    public List<V> getListaPreOrden() {
        List<V> resultado = new ArrayList<>();
        preOrden(raiz, resultado);
        return resultado;
    }

    private void preOrden(Nodo<K, V> nodo, List<V> lista) {
        if (nodo != null) {
            lista.add(nodo.valor);  // Agregar el valor del nodo
            preOrden(nodo.menor, lista);  // Recursión en el subárbol izquierdo
            preOrden(nodo.mayor, lista);  // Recursión en el subárbol derecho
        }
    }

    // Lista en postorden: izquierda - derecha - raíz
    public List<V> getListaPostOrden() {
        List<V> resultado = new ArrayList<>();
        postOrden(raiz, resultado);
        return resultado;
    }

    private void postOrden(Nodo<K, V> nodo, List<V> lista) {
        if (nodo != null) {
            postOrden(nodo.menor, lista);  // Recursión en el subárbol izquierdo
            postOrden(nodo.mayor, lista);  // Recursión en el subárbol derecho
            lista.add(nodo.valor);  // Agregar el valor del nodo
        }
    }

    // Lista en orden central (inorden): izquierda - raíz - derecha
    public List<V> getListaOrdenCentral() {
        List<V> resultado = new ArrayList<>();
        inOrden(raiz, resultado);
        return resultado;
    }

    private void inOrden(Nodo<K, V> nodo, List<V> lista) {
        if (nodo != null) {
            inOrden(nodo.menor, lista);  // Recursión en el subárbol izquierdo
            lista.add(nodo.valor);  // Agregar el valor del nodo
            inOrden(nodo.mayor, lista);  // Recursión en el subárbol derecho
        }
    }



    //Operaciones
    public void addNodoABB(K clave, V valor) {
        raiz = addNodoABB(raiz, clave, valor);
    }

    private Nodo<K,V> addNodoABB(Nodo<K,V> nodo, K clave, V valor) {
        if (nodo == null) {
            return new Nodo<>(clave, valor);
        }

        if (clave.compareTo(nodo.clave) < 0) {
            nodo.menor = addNodoABB(nodo.menor, clave, valor);
        } else if (clave.compareTo(nodo.clave) > 0) {
            nodo.mayor = addNodoABB(nodo.mayor, clave, valor);
        } else {
            nodo.valor = valor;
            return nodo;
        }

        // Actualiza la altura y balancea
        nodo.altura = 1 + Math.max(getAltura(nodo.menor), getAltura(nodo.mayor));
        return balancear(nodo);
    }

    //Balanceado
    private int getBalance(Nodo<K, V> nodo) {
        return nodo == null ? 0 : getAltura(nodo.menor) - getAltura(nodo.mayor);
    }

    public Nodo<K,V> balancear(Nodo<K,V> nodo){
        int balance = getBalance(nodo);

        // Rotación izquierda
        if (balance > 1 && getBalance(nodo.menor) >= 0) {
            return rotarDerecha(nodo);
        }

        // Rotación derecha
        if (balance < -1 && getBalance(nodo.mayor) <= 0) {
            return rotarIzquierda(nodo);
        }

        // Rotación izquierda-derecha
        if (balance > 1 && getBalance(nodo.menor) < 0) {
            nodo.menor = rotarIzquierda(nodo.menor);
            return rotarDerecha(nodo);
        }

        // Rotación derecha-izquierda
        if (balance < -1 && getBalance(nodo.mayor) > 0) {
            nodo.mayor = rotarDerecha(nodo.mayor);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private Nodo<K, V> rotarDerecha(Nodo<K, V> y) {
        Nodo<K, V> x = y.menor;
        Nodo<K, V> T2 = x.mayor;

        // Realiza la rotación
        x.mayor = y;
        y.menor = T2;

        // Actualiza las alturas
        y.altura = 1 + Math.max(getAltura(y.menor), getAltura(y.mayor));
        x.altura = 1 + Math.max(getAltura(x.menor), getAltura(x.mayor));

        return x; // Nueva raíz
    }

    private Nodo<K, V> rotarIzquierda(Nodo<K, V> x) {
        Nodo<K, V> y = x.mayor;
        Nodo<K, V> T2 = y.menor;

        // Realiza la rotación
        y.menor = x;
        x.mayor = T2;

        // Actualiza las alturas
        x.altura = 1 + Math.max(getAltura(x.menor), getAltura(x.mayor));
        y.altura = 1 + Math.max(getAltura(y.menor), getAltura(y.mayor));

        return y; // Nueva raíz
    }

    //Subárboles
    public ArbolBinarioDeBusqueda<K, V> getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<K, V> subArbol = new ArbolBinarioDeBusqueda<>();
        subArbol.raiz = raiz != null ? raiz.menor : null;
        return subArbol;
    }
    public ArbolBinarioDeBusqueda<K, V> getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<K, V> subArbol = new ArbolBinarioDeBusqueda<>();
        subArbol.raiz = raiz != null ? raiz.mayor : null;
        return subArbol;
    }

    //Responder preguntas
    public int getGrado(Nodo<K, V> nodo) {
        if (nodo == null) {
            return 0;
        }else {
            return Math.max(getGrado(nodo.menor), getGrado(nodo.mayor)) + 1;
        }
    }

    private int getAltura(Nodo<K, V> node) {
        return node == null ? 0 : node.altura;
    }

    public List<V> getListaDatosNivel(int nivel) {
        List<V> list = new ArrayList<>();
        getListaDatosNivel(raiz, nivel, 0, list);
        return list;
    }

    private void getListaDatosNivel(Nodo<K, V> nodo, int nivel, int actual, List<V> list) {
        if (nodo == null) {
            return;
        }
        if (actual == nivel) {
            list.add(nodo.valor);
        } else {
            getListaDatosNivel(nodo.menor, nivel, actual + 1, list);
            getListaDatosNivel(nodo.mayor, nivel, actual + 1, list);
        }
    }

    //Características Arbol
    public boolean isArbolHomogeneo(Nodo<K,V> nodo) {
        if (raiz == null) return true;
        if (nodo.menor == null && nodo.mayor == null) return true;
        if (nodo.menor != null && nodo.mayor != null) {
            return isArbolHomogeneo(nodo.menor) && isArbolHomogeneo(nodo.mayor);
        }
        return false;
    }

    public boolean isArbolCompleto(Nodo<K,V> nodo, int index, int numeroNodos){
        if (nodo == null) return true;
        if (index >= numeroNodos) return false;
        return isArbolCompleto(nodo.menor, 2 * index + 1, numeroNodos) &&
                isArbolCompleto(nodo.mayor, 2 * index + 2, numeroNodos);
    }

    public int getCantidadNodos(Nodo<K, V> nodo) {
        if (nodo == null) return 0;
        return 1 + getCantidadNodos(nodo.menor) + getCantidadNodos(nodo.mayor);
    }

    public boolean isArbolCasiCompleto(Nodo<K, V> nodo, int index, int numeroNodos) {
        if (nodo == null) return true;
        if (index >= numeroNodos) return false;
        return isArbolCasiCompleto(nodo.menor, 2 * index + 1, getCantidadNodos(nodo.menor)) &&
                isArbolCasiCompleto(nodo.mayor, 2 * index + 2, getCantidadNodos(nodo.mayor));
    }

    public List<K> getCamino() {
        List<K> camino = new ArrayList<>();
        getCaminoRecursivo(raiz, camino);
        return camino;
    }

    private void getCaminoRecursivo(Nodo<K, V> nodo, List<K> camino) {
        if (nodo != null) {
            camino.add(nodo.clave);
            getCaminoRecursivo(nodo.menor, camino);
            getCaminoRecursivo(nodo.mayor, camino);
        }
    }
}
