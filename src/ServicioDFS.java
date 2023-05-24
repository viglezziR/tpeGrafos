import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//Servicio DFS: recorre en profundidad y retorna un orden posible de descubrimiento para los vertices durante ese recorrido.

public class ServicioDFS<T> {
	private Grafo<T> grafo;
	
	public ServicioDFS (Grafo<T> grafo) {
		this.grafo = grafo;
	}
	
	/**
	 * Complejidad: O(V + E) donde V es el número de vértices y E es el número de adyacentes
	 * recorridos en su total en profundidad.
	 */
	
	public List<Integer> dfsForest() {
		List<Integer> ordenDescubrimiento = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();
		
		//Obtener todos los vertices del grafo
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		while (vertices.hasNext()) {
			int vertice = vertices.next();
			if (!visitados.contains(vertice)) {
				dfs(vertice, visitados, ordenDescubrimiento);
			}
		}
		return ordenDescubrimiento;
	}
	
	private void dfs(int vertice, Set<Integer> visitados, List<Integer> ordenDescubrimiento) {
		visitados.add(vertice);
		ordenDescubrimiento.add(vertice);
		
		//Obtener los vertices adyacentes no visitados
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
		while (adyacentes.hasNext()) {
			int adyacente = adyacentes.next();
			if (!visitados.contains(adyacente)) {
				dfs(adyacente, visitados, ordenDescubrimiento);
			}
		}
		
	}
	
}
