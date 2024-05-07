package Entidades;

import Entidades.Arco;
import Entidades.Grafo;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, HashSet<Arco<T>>> vertices;  //¿puedo usar un TreeSet? guarda ordenados los valores
    private int cantVertices;
    private int cantArcos;

    public GrafoDirigido(){
        vertices=new HashMap<Integer, HashSet<Arco<T>>>();
        cantVertices=0;
        cantArcos=0;
    }
    @Override
    public void agregarVertice(int verticeId) {
        vertices.put(verticeId, new HashSet<>());
        cantVertices++;
    }

    @Override
    public void borrarVertice(int verticeId) {
        vertices.remove(verticeId);
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        this.agregarVertice(verticeId1);
        this.agregarVertice(verticeId2);
        Arco<T> arcoNuevo=new Arco<T>(verticeId1, verticeId2, etiqueta);
        this.vertices.get(verticeId1).add(arcoNuevo);
        cantArcos++;
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        Arco<T>arco=this.obtenerArco(verticeId1, verticeId2);
        if (arco!=null){
            this.vertices.get(verticeId1).remove(arco);
            cantArcos--;
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        Arco<T>arco=obtenerArco(verticeId1, verticeId2);
        return arco != null;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        Iterator<Arco<T>>it=vertices.get(verticeId1).iterator();
        while (it.hasNext()) {
            Arco<T> arco = it.next();
            if (arco.getVerticeDestino() == verticeId2) {
                return arco;
            }
        }
        return null;

    }

    @Override
    public int cantidadVertices() {
        //usar vertices.size() en vez del atributo es más complejo?
        return this.cantVertices;
    }

    @Override
    public int cantidadArcos() {
        return cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        if (!vertices.containsKey(verticeId)) {
            return null;
        }
        // Obtener el conjunto de arcos salientes del vértice
        HashSet<Arco<T>> arcosSalientes = vertices.get(verticeId);
        // Crear un conjunto para almacenar los identificadores de vértices adyacentes
        HashSet<Integer> adyacentes = new HashSet<>();
        // Iterar sobre los arcos salientes y agregar los vértices de destino al conjunto de adyacentes
        for (Arco<T> arco : arcosSalientes) {
            adyacentes.add(arco.getVerticeDestino());
        }
        // Devolver un iterador sobre el conjunto de identificadores de vértices adyacentes
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        List<Arco<T>> arcos = new ArrayList<>();
        Iterator<Integer> iterator = this.obtenerVertices();

        // Mientras haya vértices en el grafo
        while (iterator.hasNext()) {
            Integer vertice = iterator.next();
            arcos.addAll(vertices.get(vertice));
        }

        // Devolver un iterador sobre la lista de arcos
        return arcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        //obtengo los arcos de ese vertice y retorno su iterador
        return this.vertices.get(verticeId).iterator();
    }
}
