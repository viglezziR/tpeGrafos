import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//Servicio DFS: recorre en profundidad y retorna un orden posible de descubrimiento para los vertices durante ese recorrido.

public class GrafoDFS<T> {
	private Grafo<T> grafo;
	
	public GrafoDFS (Grafo<T> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> dfsForest() {
		List<Integer> descubiertos = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();
		
		Iterator<Integer> vertices = this.grafo.obtenerVertices();
		while (grafo.obtenerVertices().hasNext()) {
			if(!descubiertos.contains(vertices.next())) {
				this.dfs(vertices.next(), visitados, descubiertos);
			}
		}
		
		return descubiertos;
	}
	
	private void dfs(int vertice, Set<Integer> visitados, List<Integer> ordenDescubrimiento) {
		visitados.add(vertice);
		ordenDescubrimiento.add(vertice);
		
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
		while (adyacentes.hasNext()) {
			if(!visitados.contains(adyacentes.next())) {
				this.dfs(adyacentes.next(), visitados, ordenDescubrimiento);
			}
		}
	}
	
}
