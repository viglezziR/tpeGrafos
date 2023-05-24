import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Realiza un recorrido en anchura y retorna un orden posible de descubrimiento para los vértices durante ese recorrido.

public class ServicioBFS<T> {
	
	private Grafo<T> grafo;
	
	public ServicioBFS(Grafo<T> grafo) {
		this.grafo = grafo;
	}
	
	/**
     * Complejidad: O(V + E) donde V es el número de vértices y E es el número de aristas
     * debido a que se recorren todos los vértices y se visitan todos sus adyacentes una vez.
     */
	
	public List<Integer> bfsForest(){
		List<Integer> descubiertos = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();
		
		//Obtengo vertices del grafo
		Iterator<Integer> vertices = grafo.obtenerVertices();
		while (vertices.hasNext()) {
			if(!visitados.contains(vertices.next())) {
				bfs(vertices.next(), visitados, descubiertos);
			}
		}
		
		return descubiertos;
		
	}

	private void bfs(int vertice, Set<Integer> visitados, List<Integer> descubiertos) {
	    ArrayList<Integer> cola = new ArrayList<>();
	    cola.add(vertice);
	    visitados.add(vertice);

	    int index = 0;
	    while (index < cola.size()) {
	        int actual = cola.get(index);
	        descubiertos.add(actual);

	        // Obtener los vertices adyacentes no visitados
	        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
	        while (adyacentes.hasNext()) {
	            int adyacente = adyacentes.next();
	            if (!visitados.contains(adyacente)) {
	                cola.add(adyacente);
	                visitados.add(adyacente);
	            }
	        }

	        index++;
	    }
	}
	
	
	
}
