import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {

	public static <T> void main(String[] args) {
		
		GrafoDirigido grafo2 = new GrafoDirigido<>();
        grafo2.agregarVertice(1);
        grafo2.agregarVertice(2);
        grafo2.agregarVertice(3);
        grafo2.agregarVertice(4);
        grafo2.agregarArco(1, 2, "Arco 1");
        grafo2.agregarArco(1, 3, "Arco 2");
        grafo2.agregarArco(1, 4, "Arco 3");
        grafo2.agregarArco(2, 4, "Arco 4");
        grafo2.agregarArco(3, 4, "Arco 5");
               
        System.out.println(grafo2.obtenerArco(1, 2));
	}

}
