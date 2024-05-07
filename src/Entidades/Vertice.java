package Entidades;

import Entidades.Arco;

import java.util.LinkedList;

public class Vertice<T> {
    private int verticeId;
    //estos arcos pueden ser los de entrada, salida o ambos
    private LinkedList<Arco<T>> arcos;
    public Vertice(int id, LinkedList<Arco<T>>arcos){
        this.verticeId=id;
        this.arcos=arcos;
    }

    public int getVerticeId() {
        return verticeId;
    }

    public LinkedList<Arco<T>> getArcos() {
        return arcos;
    }
}
