import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ArbolBinarioDeBusquedaEnterosTest {

    private ArbolBinarioDeBusquedaEnteros arbol;

    @BeforeEach
    public void setUp() {
        arbol = new ArbolBinarioDeBusquedaEnteros();

        // Agregamos nodos al árbol
        arbol.addNodoABB(5, 5);
        arbol.addNodoABB(3, 3);
        arbol.addNodoABB(8, 8);
        arbol.addNodoABB(2, 2);
        arbol.addNodoABB(4, 4);
    }

    @Test
    public void testGetSuma() {
        assertEquals(22, arbol.getSuma(), "La suma de los valores del árbol debería ser 22.");
    }

    @Test
    public void testGetSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros subArbolIzq = arbol.getSubArbolIzquierda();
        assertEquals(List.of(3, 2, 4), subArbolIzq.getListaPreOrden(), "El subárbol izquierdo no es correcto.");
    }
    @Test
    public void testGetSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros subArbolDer = arbol.getSubArbolDerecha();
        assertEquals(List.of(8), subArbolDer.getListaPreOrden(), "El subárbol derecho no es correcto.");
    }

    @Test
    public void testGetListaPreOrden() {
        assertEquals(List.of(5, 3, 2, 4, 8), arbol.getListaPreOrden(), "El recorrido PreOrden no coincide con lo esperado.");
    }

    @Test
    public void testGetListaPostOrden() {
        assertEquals(List.of(2, 4, 3, 8, 5), arbol.getListaPostOrden(), "El recorrido PostOrden no coincide con lo esperado.");
    }

    @Test
    public void testGetListaOrdenCentral() {
        assertEquals(List.of(2, 3, 4, 5, 8), arbol.getListaOrdenCentral(), "El recorrido OrdenCentral no coincide con lo esperado.");
    }
}
