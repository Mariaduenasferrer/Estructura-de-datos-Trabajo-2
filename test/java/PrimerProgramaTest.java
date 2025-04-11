
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimerProgramaTest {

    @Test
    void testPrimerPrograma() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        // 1. Añadir los números de 0 a 128 en orden
        for (int i = 0; i <= 128; i++) {
            arbol.addNodoABB(i, i);
        }
        assertEquals((128 * (128 + 1)) / 2, arbol.getSuma()); // Comprobar suma total

        // 2. Verificar los recorridos
        assertNotNull(arbol.getListaPreOrden());
        assertNotNull(arbol.getListaPostOrden());
        assertNotNull(arbol.getListaOrdenCentral());

        // 3. Verificar suma de los subárboles
        int sumaIzquierda = arbol.getSubArbolIzquierda().getSuma();
        int sumaDerecha = arbol.getSubArbolDerecha().getSuma();
        int sumaRaiz = arbol.raiz.getValor();

        assertEquals(arbol.getSuma(), sumaIzquierda + sumaDerecha + sumaRaiz);

        // 4. Verificar la altura del árbol
        assertEquals(8, arbol.getGrado(arbol.raiz)); // Altura esperada para 128 elementos

        // 5. Camino para llegar al valor 110
        Nodo<Integer, Integer> nodoActual = arbol.raiz;
        int longitudCamino = 0;
        while (nodoActual != null && nodoActual.getClave() != 110) {
            nodoActual = (110 < nodoActual.getClave()) ? nodoActual.getMenor() : nodoActual.getMayor();
            longitudCamino++;
        }
        assertEquals(6, longitudCamino); // Camino esperado según la estructura del árbol

        // 6. Comprobar que la suma total coincide
        assertTrue(sumaIzquierda + sumaDerecha + sumaRaiz == arbol.getSuma());
    }
}
