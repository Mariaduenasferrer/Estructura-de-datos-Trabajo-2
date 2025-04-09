import java.util.ArrayList;
import java.util.List;
// Clase específica para un Árbol Binario de Búsqueda de enteros
public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer, Integer> {

    // Metodo para calcular la suma de los valores del árbol
    public int getSuma() {
        return calcularSuma(raiz); // Llamada al metodo recursivo con la raíz del árbol
    }

    // Metodo recursivo para calcular la suma de los valores
    private int calcularSuma(Nodo<Integer, Integer> nodo) {
        if (nodo == null) {
            return 0; // Caso base: si el nodo es null, la suma es 0
        }

        // Suma el valor del nodo actual y llama recursivamente a los hijos izquierdo y derecho
        return nodo.getValor() + calcularSuma(nodo.getMenor()) + calcularSuma(nodo.getMayor());
    }
    // Metodo sobrescrito para devolver un subárbol izquierdo como instancia de la subclase
    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros subArbol = new ArbolBinarioDeBusquedaEnteros();
        subArbol.raiz = this.raiz != null ? this.raiz.menor : null;
        return subArbol;
    }

    // Metodo sobrescrito para devolver un subárbol derecho como instancia de la subclase
    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros subArbol = new ArbolBinarioDeBusquedaEnteros();
        subArbol.raiz = this.raiz != null ? this.raiz.mayor : null;
        return subArbol;
    }
    // Recorrido PreOrden (Raíz -> Izquierda -> Derecha)
    public List<Integer> getListaPreOrden() {
        List<Integer> lista = new ArrayList<>();
        recorridoPreOrden(raiz, lista);
        return lista;
    }

    private void recorridoPreOrden(Nodo<Integer, Integer> nodo, List<Integer> lista) {
        if (nodo == null) {
            return;
        }
        lista.add(nodo.getValor());
        recorridoPreOrden(nodo.getMenor(), lista);
        recorridoPreOrden(nodo.getMayor(), lista);
    }

    // Recorrido PostOrden (Izquierda -> Derecha -> Raíz)
    public List<Integer> getListaPostOrden() {
        List<Integer> lista = new ArrayList<>();
        recorridoPostOrden(raiz, lista);
        return lista;
    }

    private void recorridoPostOrden(Nodo<Integer, Integer> nodo, List<Integer> lista) {
        if (nodo == null) {
            return;
        }
        recorridoPostOrden(nodo.getMenor(), lista);
        recorridoPostOrden(nodo.getMayor(), lista);
        lista.add(nodo.getValor());
    }

    // Recorrido OrdenCentral (Izquierda -> Raíz -> Derecha)
    public List<Integer> getListaOrdenCentral() {
        List<Integer> lista = new ArrayList<>();
        recorridoOrdenCentral(raiz, lista);
        return lista;
    }

    private void recorridoOrdenCentral(Nodo<Integer, Integer> nodo, List<Integer> lista) {
        if (nodo == null) {
            return;
        }
        recorridoOrdenCentral(nodo.getMenor(), lista);
        lista.add(nodo.getValor());
        recorridoOrdenCentral(nodo.getMayor(), lista);
    }
}


