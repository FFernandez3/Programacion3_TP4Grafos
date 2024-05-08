package Recorridos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

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

    public DepthFirstSearch() {
        this.colores = new HashMap<Integer, String>();
        this.tieneCiclo=false;
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
}
