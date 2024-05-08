package Recorridos;

import Entidades.GrafoDirigido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/*EJERCICIO 4
* Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que el
grafo de entrada es acíclico*/
public class CaminoMasLargo<T> {
    private GrafoDirigido<T> grafo;
    private List<Integer> caminoMasLargo;

    public CaminoMasLargo(GrafoDirigido<T> grafo) {
        this.grafo = grafo;
        this.caminoMasLargo = new ArrayList<>();
    }
    public List<Integer> encontrarCaminoMasLargo(int inicio, int destino) {
        List<Integer> caminoActual = new ArrayList<>();
        HashSet<Integer> visitados = new HashSet<>();

        // Realizar DFS para encontrar todos los caminos posibles
        dfs(inicio, destino, visitados, caminoActual);

        return caminoMasLargo;
    }

    private void dfs(int actual, int destino, HashSet<Integer> visitados, List<Integer> caminoActual) {
        // Marcar el vértice actual como visitado
        visitados.add(actual);
        // Agregar el vértice actual al camino actual
        caminoActual.add(actual);

        // Si el vértice actual es el destino, comprobar si este camino es el más largo
        if (actual == destino) {
            if (caminoActual.size() > caminoMasLargo.size()) {
                caminoMasLargo = new ArrayList<>(caminoActual);
            }
        } else {
            // Obtener los vértices adyacentes al vértice actual
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int adyacente = adyacentes.next();
                // Si el vértice adyacente no ha sido visitado, continuar con la búsqueda
                if (!visitados.contains(adyacente)) {
                    dfs(adyacente, destino, visitados, caminoActual);
                }
            }
        }

        // Desmarcar el vértice actual y eliminarlo del camino actual para retroceder
        visitados.remove(actual);
        caminoActual.remove(caminoActual.size() - 1);
    }
}

