import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SegundoProgramaTest {

    private ArbolBinarioDeBusquedaEnteros arbol;
    private List<Integer> numeros;

    @BeforeEach
    public void setUp() {
        arbol = new ArbolBinarioDeBusquedaEnteros();
        numeros = new ArrayList<>();
        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }
        // Se mezcla la lista de forma determinista usando un Random con semilla fija
        Collections.shuffle(numeros, new Random(42));
        // Se insertan los números en el árbol
        for (int num : numeros) {
            arbol.addNodoABB(num, num);
        }
    }

    @Test
    public void testSumaTotal() {
        // La suma de los números del 0 al 128 es: 128*129/2 = 8256
        int sumaTotal = arbol.getSuma();
        assertEquals(8256, sumaTotal, "La suma total del árbol debe ser 8256");
    }

    @Test
    public void testTraversalsSum() {
        // Se obtienen las sumas a través de los recorridos
        int sumaPreOrden   = sumarLista(arbol.getListaPreOrden());
        int sumaInOrden    = sumarLista(arbol.getListaOrdenCentral());
        int sumaPostOrden  = sumarLista(arbol.getListaPostOrden());
        int sumaTotal      = arbol.getSuma();

        // Se verifica que cada método de recorrido aporte la misma suma total
        assertEquals(sumaTotal, sumaPreOrden, "La suma en preorden debe coincidir con la suma total");
        assertEquals(sumaTotal, sumaInOrden,  "La suma en inorden debe coincidir con la suma total");
        assertEquals(sumaTotal, sumaPostOrden,"La suma en postorden debe coincidir con la suma total");
    }

    @Test
    public void testSubtreeSum() {
        // Se obtiene la suma del subárbol izquierdo, derecho y el valor en la raíz
        int sumaIzquierda = arbol.getSubArbolIzquierda().getSuma();
        int sumaDerecha   = arbol.getSubArbolDerecha().getSuma();
        int raizValor     = obtenerValorRaiz(arbol);
        int sumaSubArboles= sumaIzquierda + raizValor + sumaDerecha;
        int sumaTotal     = arbol.getSuma();

        // Se verifica que la suma de la raíz y de ambos subárboles coincide con la suma total
        assertEquals(sumaTotal, sumaSubArboles,
                "La suma de la raíz y de los subárboles debe coincidir con la suma total.");
    }

    @Test
    public void testHeight() {
        // Se calcula la altura del árbol
        int altura = obtenerAltura(arbol);
        // En el peor de los casos (arbol degenerado) la altura es 129 y en un árbol balanceado es menor.
        assertTrue(altura > 0 && altura <= 129,
                "La altura del árbol debe ser entre 1 y 129, siendo menor en un árbol balanceado.");
    }

    @Test
    public void testCamino() {
        // Se obtiene el camino para llegar al valor 110
        List<Integer> camino110 = arbol.getCamino(110);
        assertNotNull(camino110, "El camino hacia 110 no debe ser null");
        assertFalse(camino110.isEmpty(), "El camino hacia 110 no debe estar vacío");

        // Se asume que el camino empieza en la raíz y termina en el nodo 110
        // Por lo tanto, debería contener el valor 110.
        assertTrue(camino110.contains(110), "El camino debe contener el valor 110");

        // Adicionalmente, se verifica que la longitud del camino (número de saltos) es camino.size()-1
        int saltos = camino110.size() - 1;
        assertTrue(saltos >= 0, "La longitud del camino (número de saltos) debe ser 0 o mayor");
    }

    // Método auxiliar para sumar los elementos de una lista de enteros
    private int sumarLista(List<Integer> lista) {
        int suma = 0;
        for (int n : lista) {
            suma += n;
        }
        return suma;
    }

    // Método auxiliar para obtener el valor de la raíz; se toma el primer elemento del recorrido en preorden.
    private int obtenerValorRaiz(ArbolBinarioDeBusquedaEnteros arbol) {
        List<Integer> preOrden = arbol.getListaPreOrden();
        if (!preOrden.isEmpty()) {
            return preOrden.getFirst();
        }
        return 0;
    }

    // Método auxiliar para calcular la altura del árbol de forma similar al main
    private int obtenerAltura(ArbolBinarioDeBusquedaEnteros arbol) {
        return calcularAltura(arbol.raiz);
    }

    // Método recursivo para calcular la altura dado un nodo.
    private int calcularAltura(Nodo<Integer, Integer> nodo) {
        if (nodo == null) return 0;
        int altIzq = calcularAltura(nodo.getMenor());
        int altDer = calcularAltura(nodo.getMayor());
        return 1 + Math.max(altIzq, altDer);
    }
}