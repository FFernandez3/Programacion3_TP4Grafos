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

    /*EJERCICIO 6
    Supongamos que una ciudad se encuentra modelada mediante un grafo, donde cada nodo
es una esquina, y las aristas representan las calles. Diseñe un algoritmo tal que dadas dos
esquinas, devuelva el camino más corto entre ambas de manera de caminar la menor
cantidad de cuadras posible*/
    public List<Integer> caminoMasCortoEntreEsquinas(Integer origen, Integer destino){
        Queue<Integer> fila = new LinkedList<>();
        HashMap<Integer, Boolean> visitados = new HashMap<>();
        HashMap<Integer, Integer> padre = new HashMap<>();
        List<Integer> camino = new ArrayList<>();

        // Marcar todos los vértices como NO_VISITADO y asignar un padre para cada vértice
        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext(); ) {
            int vertice = it.next();
            visitados.put(vertice, false);
            padre.put(vertice, null);
        }

        // Iniciar BFS desde la esquinaInicio
        caminoMasCorto(origen, visitados, padre, fila);

        // Reconstruir el camino más corto desde esquinaInicio hasta esquinaFin
        Integer actual = destino;
        while (actual != null) {
            camino.add(actual);
            actual = padre.get(actual);
        }
        Collections.reverse(camino); // Invertir el camino para que comience desde esquinaInicio

        return camino;
    }


/*Refactor del bfs para el ejercicio 6 */
    private void caminoMasCorto(Integer origen, HashMap<Integer, Boolean>visitados, HashMap<Integer, Integer> padre,  Queue<Integer> fila ) {
        // Marcar el vértice s como VISITADO (true)
        visitados.put(origen, true);
        // Agregar s a la fila
        fila.offer(origen);

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
                    // Asignar x como el padre de y en el camino
                    padre.put(y, x);
                    // Agregar y a la fila
                    fila.offer(y);
                }
            }
        }
    }
   }








