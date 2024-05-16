package Recorridos;

import java.util.*;

import Entidades.Grafo;
/*
* EJERCICIO 2
* Implemente los recorridos Depth-First-Search y Breadth-First-Search*/

/*EJERCICIO 3
* Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo
* */
public class DepthFirstSearch <T>{
    private HashMap<Integer, String> colores;

    /*Para el ejercicio 3 */
    private boolean tieneCiclo;
    //tener un grafo como atributo?
    /*para el ejercicio 5:*/
   // private Map<Integer, List<List<Integer>>> caminos; // Para almacenar los caminos desde cada vértice hasta el objetivo

    public DepthFirstSearch() {
        this.colores = new HashMap<Integer, String>();
        this.tieneCiclo=false;
       // this.caminos=new HashMap<>();
    }

    public void dfs(Grafo<T> g){
        Iterator<Integer> it= g.obtenerVertices();
        while(it.hasNext()){
            Integer vertice=it.next();
            colores.put(vertice, "BLANCO");
        }
        Iterator<Integer> it1= g.obtenerVertices();
        while ((it1.hasNext())
        ) {
            Integer vertice=it.next();
            if(Objects.equals(colores.get(vertice), "BLANCO")){
                dfsVisit(g, vertice);
            }

        }
    }
    private void dfsVisit(Grafo<T> grafo, Integer vertice){
        colores.put(vertice, "AMARILLO");
        Iterator<Integer>adyacentes=grafo.obtenerAdyacentes(vertice);
        while(adyacentes.hasNext()){
            Integer v=adyacentes.next();
            if(colores.get(v)=="BLANCO"){
                dfsVisit(grafo, v);
            }
            /*para el ejercicio 3*/
            else if (colores.get(v)=="AMARILLO") {
                this.tieneCiclo=true;
                return;
            }
            colores.put(v, "NEGRO");
        }
    }

    /*EJERCICIO 5
    * Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
con todos los vértices a partir de los cuales exista un camino en G que termine en v*/

    public List<List<Integer>> buscarCaminosHastaV(Grafo<T> g, int verticeObjetivo) {
        this.colores.clear(); // Limpiar los colores previos
       // this.caminos.clear(); // Limpiar los caminos previos
        List<List<Integer>> resultado = new ArrayList<List<Integer>>();

        // Marcar todos los vértices como blancos
        Iterator<Integer> it = g.obtenerVertices();
        while (it.hasNext()) {
            Integer vertice = it.next();
            this.colores.put(vertice, "BLANCO");
        }

        // Realizar la búsqueda en profundidad desde cada vértice
        for (Iterator<Integer> iter = g.obtenerVertices(); iter.hasNext(); ) {
            Integer vertice = iter.next();
            if (this.colores.get(vertice).equals("BLANCO")) {
                List<Integer> caminoActual = new ArrayList<>();
                dfsEjercicio5(g, vertice, verticeObjetivo, caminoActual);
                if (caminoActual.contains(verticeObjetivo)) {
                    resultado.add(caminoActual);
                }
            }
        }

        return resultado;
    }

    /*es un refactor del dfsVisit para el ejercicio 5 */
    private void dfsEjercicio5(Grafo<T> grafo, Integer vertice, Integer verticeObjetivo, List<Integer> caminoActual) {
        this.colores.put(vertice, "AMARILLO"); // Marcar el vértice actual como amarillo
        caminoActual.add(vertice); // Agregar el vértice actual al camino actual

        if(vertice.equals(verticeObjetivo)){
            return;
        }

        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        for (Iterator<Integer> it = adyacentes; it.hasNext(); ) {
            Integer v = it.next();

            if (this.colores.get(v).equals("BLANCO")) {
                // Si el vértice adyacente es blanco, realizar la búsqueda en profundidad desde él
                dfsEjercicio5(grafo, v, verticeObjetivo, caminoActual);
            }
        }
        this.colores.put(vertice, "NEGRO"); // Marcar el vértice actual como negro después de visitarlo
        caminoActual.remove(vertice); // Remover el vértice actual del camino actual
    }
}
