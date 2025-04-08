public class Arbolprueba {
    import java.util.ArrayList;
    import java.util.List;

    // Interfaz Comparable debe ser implementada por el tipo de claves
    public class ArbolBinarioDeBusqueda<K extends Comparable<K>, V> {
        private Nodo<K, V> raiz;

        // Clase interna para los nodos del árbol
        private static class Nodo<K, V> {
            K clave;
            V valor;
            Nodo<K, V> izquierda, derecha;

            Nodo(K clave, V valor) {
                this.clave = clave;
                this.valor = valor;
                izquierda = derecha = null;
            }
        }

        // Método para añadir un nuevo elemento
        public void add(K clave, V valor) {
            raiz = addRec(raiz, clave, valor);
        }

        private Nodo<K, V> addRec(Nodo<K, V> nodo, K clave, V valor) {
            if (nodo == null) {
                return new Nodo<>(clave, valor);
            }
            if (clave.compareTo(nodo.clave) < 0) {
                nodo.izquierda = addRec(nodo.izquierda, clave, valor);
            } else if (clave.compareTo(nodo.clave) > 0) {
                nodo.derecha = addRec(nodo.derecha, clave, valor);
            }
            return nodo;
        }

        // Métodos solicitados
        public int getGrado() {
            return (raiz == null) ? 0 : grado(raiz);
        }

        private int grado(Nodo<K, V> nodo) {
            if (nodo == null) return 0;
            return Math.max(grado(nodo.izquierda), grado(nodo.derecha)) + 1;
        }

        public int getAltura() {
            return altura(raiz);
        }

        private int altura(Nodo<K, V> nodo) {
            if (nodo == null) return -1;
            return Math.max(altura(nodo.izquierda), altura(nodo.derecha)) + 1;
        }

        public List<V> getListaDatosNivel(int nivel) {
            List<V> lista = new ArrayList<>();
            getDatosNivel(raiz, nivel, 0, lista);
            return lista;
        }

        private void getDatosNivel(Nodo<K, V> nodo, int nivel, int actual, List<V> lista) {
            if (nodo == null) return;
            if (actual == nivel) {
                lista.add(nodo.valor);
            } else {
                getDatosNivel(nodo.izquierda, nivel, actual + 1, lista);
                getDatosNivel(nodo.derecha, nivel, actual + 1, lista);
            }
        }

        public boolean isArbolHomogeneo() {
            return isHomogeneo(raiz);
        }

        private boolean isHomogeneo(Nodo<K, V> nodo) {
            if (nodo == null) return true;
            if (nodo.izquierda == null && nodo.derecha == null) return true;
            if (nodo.izquierda != null && nodo.derecha != null) {
                return isHomogeneo(nodo.izquierda) && isHomogeneo(nodo.derecha);
            }
            return false;
        }

        public boolean isArbolCompleto() {
            return isCompleto(raiz, 0, getCantidadNodos(raiz));
        }

        private boolean isCompleto(Nodo<K, V> nodo, int index, int numeroNodos) {
            if (nodo == null) return true;
            if (index >= numeroNodos) return false;
            return isCompleto(nodo.izquierda, 2 * index + 1, numeroNodos) &&
                    isCompleto(nodo.derecha, 2 * index + 2, numeroNodos);
        }

        private int getCantidadNodos(Nodo<K, V> nodo) {
            if (nodo == null) return 0;
            return 1 + getCantidadNodos(nodo.izquierda) + getCantidadNodos(nodo.derecha);
        }

        public boolean isArbolCasiCompleto() {
            return isCasiCompleto(raiz, 0, getCantidadNodos(raiz));
        }

        private boolean isCasiCompleto(Nodo<K, V> nodo, int index, int numeroNodos) {
            if (nodo == null) return true;
            if (index >= numeroNodos) return false;
            return isCasiCompleto(nodo.izquierda, 2 * index + 1, numeroNodos) &&
                    isCasiCompleto(nodo.derecha, 2 * index + 2, numeroNodos);
        }

        public List<K> getCamino() {
            List<K> camino = new ArrayList<>();
            getCaminoRec(raiz, camino);
            return camino;
        }

        private void getCaminoRec(Nodo<K, V> nodo, List<K> camino) {
            if (nodo != null) {
                camino.add(nodo.clave);
                getCaminoRec(nodo.izquierda, camino);
                getCaminoRec(nodo.derecha, camino);
            }
        }

        public List<V> getListaPreOrden() {
            List<V> lista = new ArrayList<>();
            preOrden(raiz, lista);
            return lista;
        }

        private void preOrden(Nodo<K, V> nodo, List<V> lista) {
            if (nodo != null) {
                lista.add(nodo.valor);
                preOrden(nodo.izquierda, lista);
                preOrden(nodo.derecha, lista);
            }
        }

        public List<V> getListaPostOrden() {
            List<V> lista = new ArrayList<>();
            postOrden(raiz, lista);
            return lista;
        }

        private void postOrden(Nodo<K, V> nodo, List<V> lista) {
            if (nodo != null) {
                postOrden(nodo.izquierda, lista);
                postOrden(nodo.derecha, lista);
                lista.add(nodo.valor);
            }
        }

        public List<V> getListaOrdenCentral() {
            List<V> lista = new ArrayList<>();
            inOrden(raiz, lista);
            return lista;
        }

        private void inOrden(Nodo<K, V> nodo, List<V> lista) {
            if (nodo != null) {
                inOrden(nodo.izquierda, lista);
                lista.add(nodo.valor);
                inOrden(nodo.derecha, lista);
            }
        }

        // Métodos para obtener subárboles
        public ArbolBinarioDeBusqueda<K, V> getSubArbolIzquierda() {
            ArbolBinarioDeBusqueda<K, V> subArbol = new ArbolBinarioDeBusqueda<>();
            subArbol.raiz = raiz != null ? raiz.izquierda : null;
            return subArbol;
        }

        public ArbolBinarioDeBusqueda<K, V> getSubArbolDerecha() {
            ArbolBinarioDeBusqueda<K, V> subArbol = new ArbolBinarioDeBusqueda<>();
            subArbol.raiz = raiz != null ? raiz.derecha : null;
            return subArbol;
        }
    }
}
