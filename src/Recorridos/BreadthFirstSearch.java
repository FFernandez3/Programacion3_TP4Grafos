package Recorridos;

import Entidades.Grafo;

import java.util.*;

public class BreadthFirstSearch <T> {
    private Grafo<T> grafo;

    public BreadthFirstSearch(Grafo<T> grafo) {
        this.grafo = grafo;
    }

    public Iterator<Integer> bfs() {
        Queue<Integer> fila = new LinkedList<>();
        HashMap<Integer, Boolean> visitados = new HashMap<>();

        // Vaciar la fila
        //fila.clear();

        // Marcar todos los vértices como NO_VISITADO
        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext(); ) {
            int vertice = it.next();
            visitados.put(vertice, false);
        }

        // Recorrer cada vértice del grafo
        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext(); ) {
            int vertice = it.next();
            if (!visitados.get(vertice)) {
                bfs(vertice, visitados, fila);
            }
        }

        // Devolver un iterador sobre el orden de visita de los vértices
        return visitados.keySet().iterator();
    }


    private void bfs(int s, HashMap<Integer, Boolean> visitados, Queue<Integer> fila) {
        // Marcar el vértice s como VISITADO (true)
        visitados.put(s, true);
        // Agregar s a la fila
        fila.offer(s);

        // Mientras la fila no esté vacía
        while (!fila.isEmpty()) {
            // Tomamos vértice x de la fila y lo eliminamos
            int x = fila.poll();
            // Obtener los vértices adyacentes a x
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(x);
            // Recorrer los vértices adyacentes
            while (adyacentes.hasNext()) {
                int y = adyacentes.next();
                // Si el vértice y es NO_VISITADO (false)
                if (!visitados.get(y)) {
                    // Marcar el vértice y como VISITADO (true)
                    visitados.put(y, true);
                    // Agregar y a la fila
                    fila.offer(y);
                }
            }
        }
    }
}



