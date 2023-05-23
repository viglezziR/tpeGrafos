import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, Set<Integer>> vertices;
	private Map<Integer, Arco<T>> arcos;
	
	public Map<Integer, Set<Integer>> getVertices() {
		return vertices;
	}

	public GrafoDirigido () {
		vertices = new HashMap<>();
		arcos = new HashMap<>();
	}

	// Agrega un vertice 
	@Override
	public void agregarVertice(int verticeId) {
		if (!contieneVertice(verticeId)) {
			vertices.put(verticeId, new HashSet<Integer>());
		}
	}
	
	// Borra un vertice
	@Override
	public void borrarVertice(int verticeId) {
		if (!contieneVertice(verticeId)) {
			this.vertices.remove(verticeId);
		}
		
		/**Con cada vertice que elimino, debo eliminar el arco asociado a ese vertice
		 * ya sea de origen, o de destino.
		 */
		Iterator<Arco<T>> arcosIterator = obtenerArcos();
		while(arcosIterator.hasNext()) {
			Arco<T> arco = arcosIterator.next();
			if(arco.getVerticeOrigen() == verticeId || arco.getVerticeDestino() == verticeId) {
				arcosIterator.remove();
			}
		}
	}
	
	// Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)) {
			Arco<T> arco = new Arco<T>(verticeId1, verticeId2, etiqueta);
			arcos.put(this.getArcoCode(verticeId1, verticeId2), arco);
			vertices.get(verticeId1).add(verticeId2);
		}
	}
	
	// Borra el arco que conecta el verticeId1 con el verticeId2
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			if (arcos.containsKey(getArcoCode(verticeId1, verticeId2)));
			vertices.get(verticeId1).remove(verticeId2);
			arcos.remove(getArcoCode(verticeId1, verticeId2));
		}
		
	}
	
	// Verifica si existe un vertice
	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}
	
	// Verifica si existe un arco entre dos vertices
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return this.arcos.containsKey(getArcoCode(verticeId1, verticeId2));
	}
	
	// Obtener el arco que conecta el verticeId1 con el verticeId2
	@Override
	public Arco <T> obtenerArco(int verticeId1, int verticeId2) {
		Arco<T> arco = arcos.get(getArcoCode(verticeId1, verticeId2));
		return arco;
	}
	
	// Devuelve la cantidad total de vertices en el grafo
	@Override
	public int cantidadVertices() {
		return vertices.size();
	}
	
	// Devuelve la cantidad total de arcos en el grafo
	@Override
	public int cantidadArcos() {
		return arcos.size();
	}
	
	// Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}
	
	// Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Set<Integer> adyacentes = vertices.get(verticeId);
		if (adyacentes != null) {
			return adyacentes.iterator();
		}
		return Collections.emptyIterator();
	}
	
	// Obtiene un iterador que me permite recorrer todos los arcos del grafo
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		return arcos.values().iterator();
	}
	
	// Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		List<Arco<T>> arcosVertice = new ArrayList<>();
        for (Arco<T> arco : arcos.values()) {
            if (arco.getVerticeOrigen() == verticeId) {
                arcosVertice.add(arco);
            }
        }
        return arcosVertice.iterator();
	}
	
	/**
	 * getArcoCode(int verticeId1, int verticeId2) calcula y devuelve un 
	 * código hash unico del par verticeId1 y verticeId2 que representan un arco. 
	 */
	private int getArcoCode(int verticeId1, int verticeId2) {
        return Objects.hash(verticeId1, verticeId2);
    }
}
