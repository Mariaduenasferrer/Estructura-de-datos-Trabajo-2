import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ArbolBinarioDeBusquedaTest {

    private ArbolBinarioDeBusqueda<Integer, String> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinarioDeBusqueda<>();
    }

    @Test
    public void testListasRecorridos() {
        ArbolBinarioDeBusqueda<Integer, String> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.addNodoABB(50, "A");
        arbol.addNodoABB(30, "B");
        arbol.addNodoABB(70, "C");
        arbol.addNodoABB(20, "D");
        arbol.addNodoABB(40, "E");
        arbol.addNodoABB(60, "F");
        arbol.addNodoABB(80, "G");

        // Verificamos los resultados de los recorridos en listas
        List<String> preOrden = arbol.getListaPreOrden();
        List<String> inOrden = arbol.getListaOrdenCentral();
        List<String> postOrden = arbol.getListaPostOrden();

        // Verificación: el orden debe ser correcto según los recorridos
        assertEquals(List.of("A", "B", "D", "E", "C", "F", "G"), preOrden);
        assertEquals(List.of("D", "B", "E", "A", "F", "C", "G"), inOrden);
        assertEquals(List.of("D", "E", "B", "F", "G", "C", "A"), postOrden);
    }

    @Test
    void testAddNodoABB() {
        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        assertEquals("Raíz", arbol.raiz.valor);
        assertEquals("Izquierda", arbol.raiz.menor.valor);
        assertEquals("Derecha", arbol.raiz.mayor.valor);

        arbol.addNodoABB(15, "Izquierda");
        assertEquals("Izquierda", arbol.raiz.mayor.valor);
    }

    @Test
    void testBalancearDerecha() {
        arbol.addNodoABB(30, "Treinta");
        arbol.addNodoABB(20, "Veinte");
        arbol.addNodoABB(10, "Diez"); // Esto debería causar una rotación

        // Verifica que la raíz sea 20 después del balanceo
        Nodo<Integer, String> raiz = arbol.raiz;
        assertEquals(20, raiz.clave);
        assertEquals(30, raiz.mayor.clave);
        assertEquals(10, raiz.menor.clave);
    }
    @Test
    void testBalancearIzquierda() {
        arbol.addNodoABB(10, "Diez");
        arbol.addNodoABB(20, "Veinte");
        arbol.addNodoABB(30, "Treinta"); // Esto debería causar una rotación

        // Verifica que la raíz sea 20 después del balanceo
        Nodo<Integer, String> raiz = arbol.raiz;
        assertEquals(20, raiz.clave);
        assertEquals(30, raiz.mayor.clave);
        assertEquals(10, raiz.menor.clave);
    }
    @Test
    void testBalancearIzquierdaDerecha() {
        arbol.addNodoABB(30, "Treinta");
        arbol.addNodoABB(10, "Diez");
        arbol.addNodoABB(20, "Veinte"); // Esto debería causar una rotación izquierda-derecha

        Nodo<Integer, String> raiz = arbol.raiz;
        assertEquals(20, raiz.clave);
        assertEquals(30, raiz.mayor.clave);
        assertEquals(10, raiz.menor.clave);
    }
    @Test
    void testBalancearDerechaIzquierda() {
        arbol.addNodoABB(10, "Diez");
        arbol.addNodoABB(30, "Treinta");
        arbol.addNodoABB(20, "Veinte"); // Esto debería causar una rotación derecha-izquierda

        Nodo<Integer, String> raiz = arbol.raiz;
        assertEquals(20, raiz.clave);
        assertEquals(10, raiz.menor.clave);
        assertEquals(30, raiz.mayor.clave);
    }

    @Test
    void testGetSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<Integer, String> subArbolIzquierda1 = arbol.getSubArbolIzquierda();
        assertNull(subArbolIzquierda1.raiz);

        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");

        ArbolBinarioDeBusqueda<Integer, String> subArbolIzquierda2 = arbol.getSubArbolIzquierda();
        assertNotNull(subArbolIzquierda2.raiz);
        assertEquals("Izquierda", subArbolIzquierda2.raiz.valor);

    }

    @Test
    void testGetSubArbolDerecha() {
        ArbolBinarioDeBusqueda<Integer, String> subArbolDerecha1 = arbol.getSubArbolDerecha();
        assertNull(subArbolDerecha1.raiz);

        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(15, "Derecha");

        ArbolBinarioDeBusqueda<Integer, String> subArbolDerecha2 = arbol.getSubArbolDerecha();
        assertNotNull(subArbolDerecha2.raiz);
        assertEquals("Derecha", subArbolDerecha2.raiz.valor);
    }

    @Test
    void testGetGrado() {
        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        assertEquals(2, arbol.getGrado(arbol.raiz));
    }

    @Test
    void testGetListaDatosNivel() {
        List<String> listaNivel2 = arbol.getListaDatosNivel(2);
        assertTrue(listaNivel2.isEmpty());

        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        List<String> listaNivel0 = arbol.getListaDatosNivel(0);
        List<String> listaNivel1 = arbol.getListaDatosNivel(1);

        assertEquals(1, listaNivel0.size());
        assertEquals("Raíz", listaNivel0.get(0));
        assertEquals(2, listaNivel1.size());
        assertTrue(listaNivel1.contains("Izquierda"));
        assertTrue(listaNivel1.contains("Derecha"));
    }

    @Test
    void testIsArbolHomogeneo() {
        assertTrue(arbol.isArbolHomogeneo(arbol.raiz));

        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        assertTrue(arbol.isArbolHomogeneo(arbol.raiz));

        arbol.addNodoABB(20,"DobleDerecha");

        assertFalse(arbol.isArbolHomogeneo(arbol.raiz));

    }

    @Test
    void testIsArbolCompleto() {
        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        assertTrue(arbol.isArbolCompleto(arbol.raiz, 0, arbol.getCantidadNodos(arbol.raiz)));
    }

    @Test
    void testIsArbolCasiCompleto() {
        assertTrue(arbol.isArbolCasiCompleto(null, 0, 0));

        Nodo<Integer, String> nodoHoja = new Nodo<>(1, "Hoja");
        arbol.raiz = nodoHoja;
        assertTrue(arbol.isArbolCasiCompleto(nodoHoja, 0, 1));
        assertFalse(arbol.isArbolCasiCompleto(nodoHoja, 10, 1));
    }

    @Test
    void testGetCamino() {
        arbol.addNodoABB(10, "Raíz");
        arbol.addNodoABB(5, "Izquierda");
        arbol.addNodoABB(15, "Derecha");

        List<Integer> camino = arbol.getCamino();
        assertEquals(3, camino.size());
        assertEquals(10, camino.get(0));
        assertEquals(5, camino.get(1));
        assertEquals(15, camino.get(2));
    }
}
