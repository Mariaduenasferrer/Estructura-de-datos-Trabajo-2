import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SegundoPrograma {
    public static void main(String[] args) {
        //Esto crea el árbol de búsqueda de enteros
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        //Esto genera una lista con números de 0 a 128 sin repetir
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }
        //Esto mezcla la lista de manera aleatoria
        Collections.shuffle(numeros);

        //Esto inserta los números en el árbol
        for (int num : numeros) {
            arbol.addNodoABB(num, num);
        }

        //Esto calcula la suma total usando getSuma()
        int sumaTotal = arbol.getSuma();
        System.out.println("Suma total del árbol: " + sumaTotal);

        //Esto verifica que la suma es la misma accediendo en los 3 tipos de recorridos
        int sumaPreOrden = sumarLista(arbol.getListaPreOrden());
        int sumaInOrden = sumarLista(arbol.getListaOrdenCentral());
        int sumaPostOrden = sumarLista(arbol.getListaPostOrden());
        System.out.println("Suma (PreOrden):  " + sumaPreOrden);
        System.out.println("Suma (InOrden):   " + sumaInOrden);
        System.out.println("Suma (PostOrden): " + sumaPostOrden);

        if(sumaTotal == sumaPreOrden && sumaTotal == sumaInOrden && sumaTotal == sumaPostOrden) {
            System.out.println("La suma obtenida con los 3 recorridos coincide con la suma total.");
        } else {
            System.out.println("Error: Las sumas de los recorridos no coinciden con la suma total.");
        }

        //Esto verifica la suma al sumar subárbol izquierdo, la raíz y subárbol derecho.
        int sumaIzquierda = arbol.getSubArbolIzquierda().getSuma();
        int sumaDerecha = arbol.getSubArbolDerecha().getSuma();
        int raizValor = obtenerValorRaiz(arbol); // Método auxiliar para obtener el valor en la raíz
        int sumaSubArboles = sumaIzquierda + raizValor + sumaDerecha;
        System.out.println("Suma subárbol izquierdo: " + sumaIzquierda);
        System.out.println("Valor de la raíz:         " + raizValor);
        System.out.println("Suma subárbol derecho:    " + sumaDerecha);
        System.out.println("Suma (izq + raíz + der):  " + sumaSubArboles);
        if(sumaTotal == sumaSubArboles) {
            System.out.println("La suma de la raíz y de los subárboles coincide con la suma total.");
        } else {
            System.out.println("Error: La suma de la raíz y de los subárboles no coincide con la suma total.");
        }

        //Esto muestra la altura del árbol
        int altura = obtenerAltura(arbol);
        System.out.println("Altura del árbol: " + altura);
        System.out.println("Inserción aleatoria tiende a generar un árbol más balanceado y, por tanto, una altura menor que la inserción ordenada.");

        //Esto obtiene el camino para llegar al valor 110 y su longitud de camino
        List<Integer> camino110 = arbol.getCamino(110);
        System.out.println("Camino para llegar al 110: " + camino110);
        System.out.println("Longitud del camino (número de saltos): " + (camino110.size() - 1));
    }

    //Esto es un método auxiliar para sumar los elementos de una lista de enteros
    private static int sumarLista(List<Integer> lista) {
        int suma = 0;
        for (int n : lista) {
            suma += n;
        }
        return suma;
    }

    //Esto es un método auxiliar para obtener el valor almacenado en la raíz del árbol.
    private static int obtenerValorRaiz(ArbolBinarioDeBusquedaEnteros arbol) {
        try {
            List<Integer> preOrden = arbol.getListaPreOrden();
            if(!preOrden.isEmpty()) {
                return preOrden.getFirst();
            }
        } catch(Exception e) {
            return 0;
        }
        return 0;
    }

    //Esto es un método auxiliar para calcular la altura del árbol de forma recursiva.
    private static int obtenerAltura(ArbolBinarioDeBusquedaEnteros arbol) {
        return calcularAltura(arbol.raiz);
    }

    //Esto es un método recursivo para calcular la altura dado un nodo.
    private static int calcularAltura(Nodo<Integer, Integer> nodo) {
        if(nodo == null) return 0;
        int altIzq = calcularAltura(nodo.getMenor());
        int altDer = calcularAltura(nodo.getMayor());
        return 1 + Math.max(altIzq, altDer);
    }
}
