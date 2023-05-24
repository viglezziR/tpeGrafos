import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//Servicio caminos: dado un origen, destino y limite, retorna todos los caminos posibles con el tope de limite de arcos, y no se puede pasar mas de 1 vez por arco.

public class ServicioCaminos <T> {
	private Grafo<T> grafo;
	private int origen;
	private int destino;
	private int lim;

    public ServicioCaminos(Grafo<T> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }
    
    /**
     * Complejidad: O(n) donde n es el número total de arcos en el grafo.
     * Debido a que se realiza una acción para verificar si existe un arco en cada iteración del algoritmo.
     */
    
    public List<List<Integer>> caminos() {
        List<List<Integer>> caminos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        Set<Arco<T>> arcosVisitados = new HashSet<>();

        caminoActual.add(origen);
        encontrarCaminos(caminoActual, arcosVisitados, caminos);

        return caminos;
    }

    private void encontrarCaminos(List<Integer> caminoActual, Set<Arco<T>> arcosVisitados, List<List<Integer>> caminos) {
        int verticeActual = caminoActual.get(caminoActual.size() - 1);

        if (verticeActual == destino && caminoActual.size()-1 <= lim) {
            caminos.add(new ArrayList<>(caminoActual));
            return;
        }

        if (caminoActual.size()-1 > lim) {
            return;
        }

        Iterator<Arco<T>> arcosIterator = grafo.obtenerArcos(verticeActual);
        while (arcosIterator.hasNext()) {
            Arco<T> arco = arcosIterator.next();

            if (!arcosVisitados.contains(arco) && !existeEnCamino(arco, caminoActual)) {
                arcosVisitados.add(arco);
                caminoActual.add(arco.getVerticeDestino());

                encontrarCaminos(caminoActual, arcosVisitados, caminos);

                caminoActual.remove(caminoActual.size() - 1);
                arcosVisitados.remove(arco);
            }
        }
    }

    private boolean existeEnCamino(Arco<T> arco, List<Integer> camino) {
        int verticeOrigen = arco.getVerticeOrigen();
        int verticeDestino = arco.getVerticeDestino();

        for (int i = 0; i < camino.size() - 1; i++) {
            if (camino.get(i) == verticeOrigen && camino.get(i + 1) == verticeDestino) {
                return true;
            }
        }

        return false;
    }
}