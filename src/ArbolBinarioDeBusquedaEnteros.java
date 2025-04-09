public class ArbolBinarioDeBusquedaEnteros<K extends Comparable<K>> extends ArbolBinarioDeBusqueda<K, Integer> {

    // Método para calcular la suma de los valores en el árbol
    public int getSuma() {
        return calcularSuma(raiz); // Inicia la suma desde la raíz
    }

    // Método recursivo auxiliar para calcular la suma
    private int calcularSuma(Nodo<K, Integer> nodo) {
        if (nodo == null) {
            return 0; // Si el nodo es null, no aporta a la suma
        }
        // Suma del valor del nodo actual + suma de los subárboles izquierdo y derecho
        return nodo.getValor() + calcularSuma(nodo.getMenor()) + calcularSuma(nodo.getMayor());
    }
}

