import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {

	public static <T> void main(String[] args) {
		
		GrafoDirigido grafo = new GrafoDirigido<>();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarArco(1, 2, "Arco 1");
        grafo.agregarArco(1, 3, "Arco 2");
        grafo.agregarArco(1, 4, "Arco 3");
        grafo.agregarArco(2, 4, "Arco 4");
        grafo.agregarArco(3, 4, "Arco 5");
               
        //Imprimir la cantidad de vértices y arcos
        System.out.println("Cantidad de vértices: " + grafo.cantidadVertices());
        System.out.println("Cantidad de arcos: " + grafo.cantidadArcos());
        System.out.println();
	
        // Imprimir todos los vértices
        Iterator<Integer> verticesIterator = grafo.obtenerVertices();
        System.out.println("Vertices:");
        while (verticesIterator.hasNext()) {
        	int verticeId = verticesIterator.next();
        	System.out.println("Vertice: " + verticeId);
        }
        
        // Imprimir los arcos de cada vértice
        Iterator<Integer> verticesIterator2 = grafo.obtenerVertices();
        System.out.println("Arcos:");
        while (verticesIterator2.hasNext()) {
        	int verticeId = verticesIterator2.next();
        	Iterator<Arco<String>> arcosIterator = grafo.obtenerArcos(verticeId);
        	System.out.println("Arcos del vértice " + verticeId + ":");
        	while (arcosIterator.hasNext()) {
		Arco<String> arco = arcosIterator.next();
		int verticeOrigen = arco.getVerticeOrigen();
		int verticeDestino = arco.getVerticeDestino();
		String etiqueta = arco.getEtiqueta();
		System.out.println("Arco: " + verticeOrigen + " -> " + verticeDestino + " (Etiqueta: " + etiqueta + ")");
        	}
        }
        
        ServicioBFS servicioBFS = new ServicioBFS(grafo);
        List<Integer> ordenDescubrimiento = servicioBFS.bfsForest();

        // Imprimir el orden de descubrimiento de los vértices
        System.out.println("Orden de descubrimiento BFS:");
        for (int vertice : ordenDescubrimiento) {
            System.out.println(vertice);
        }
        
        // Crear una instancia del servicio DFS y ejecutar el recorrido
        ServicioDFS servicioDFS = new ServicioDFS(grafo);
        List<Integer> ordenDescubrimiento2 = servicioDFS.dfsForest();

        // Imprimir el orden de descubrimiento de los vértices
        System.out.println("Orden de descubrimiento DFS:");
        for (int vertice : ordenDescubrimiento2) {
            System.out.println(vertice);
        }
        
        // Crear el servicio de caminos
        ServicioCaminos servicio = new ServicioCaminos<>(grafo, 1, 4, 2);

        // Obtener los caminos
        List<List<Integer>> caminos = servicio.caminos();

        // Imprimir los caminos encontrados
        System.out.println("Caminos encontrados:");
        for (List<Integer> camino : caminos) {
        	System.out.println(camino);
        }
        
        
        
        
	}

}
