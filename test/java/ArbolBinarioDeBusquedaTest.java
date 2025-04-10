import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArbolBinarioDeBusquedaTest {

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
}

