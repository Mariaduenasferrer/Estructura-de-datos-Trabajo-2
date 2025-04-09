public class PrimerPrograma {
    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        // 1. Añadir los números de 0 a 128 en orden
        for (int i = 0; i <= 128; i++) {
            arbol.addNodoABB(i, i);
        }

        // 2. Calcular la suma de los valores
        int suma = arbol.getSuma();
        System.out.println("La suma de los valores es: " + suma);

        // 3. Verificar la suma usando los 3 tipos de recorridos posibles
        System.out.println("Recorrido PreOrden: " + arbol.getListaPreOrden());
        System.out.println("Recorrido PostOrden: " + arbol.getListaPostOrden());
        System.out.println("Recorrido OrdenCentral: " + arbol.getListaOrdenCentral());

        // 4. Verificar la suma con los elementos de los subárboles izquierdo y derecho
        int sumaIzquierda = arbol.getSubArbolIzquierda().getSuma();
        int sumaDerecha = arbol.getSubArbolDerecha().getSuma();
        System.out.println("Suma del subárbol izquierdo: " + sumaIzquierda);
        System.out.println("Suma del subárbol derecho: " + sumaDerecha);
        System.out.println("La suma total con los subárboles coincide: " + (sumaIzquierda + sumaDerecha == suma));

        // 5. Obtener la altura del árbol
        int altura = arbol.getGrado(arbol.raiz);
        System.out.println("La altura del árbol es: " + altura);

        // 6. Camino y longitud para llegar al valor 110
        Nodo<Integer, Integer> nodoActual = arbol.raiz;
        int longitudCamino = 0;
        while (nodoActual != null && nodoActual.getClave() != 110) {
            if (110 < nodoActual.getClave()) {
                nodoActual = nodoActual.getMenor();
            } else {
                nodoActual = nodoActual.getMayor();
            }
            longitudCamino++;
        }
        System.out.println("El camino para llegar al valor 110 tiene longitud: " + longitudCamino);

        // Obtener el valor de la raíz
        int valorRaiz = arbol.raiz.getValor();
        System.out.println("El valor de la raíz es: " + valorRaiz);

        /* Comprobar que la suma de los árboles izquierdo, derecho y raiz
        es el mismo que el resultado de la suma de todos los componentes del árbol
         */
        System.out.println("La suma total coincide: " + (sumaIzquierda + sumaDerecha + valorRaiz == suma));
    }
}


