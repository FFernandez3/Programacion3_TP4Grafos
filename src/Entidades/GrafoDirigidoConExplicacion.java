package Entidades;

import Entidades.Arco;
import Entidades.Grafo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigidoConExplicacion<T> implements Grafo<T> {
    //version 1
    private T[][]matriz;
    private int[] mapeo;
    private int index=0;

    //version 2
    private HashMap<Integer, Integer>mapeoHash;
    //version 3
    private LinkedList<Integer> vertices;
    private LinkedList<Arco<T>>arcos;

    //version 4 para mejorar la 3
    private LinkedList<Vertice<T>>vertices1;

    //version 5
    private HashMap<Integer, Vertice> vertices2;

    //version 3 no usa el contructor, pq no necesito setearle un tamaño de entrada
    public GrafoDirigidoConExplicacion(int cantVartices){
        mapeo=new int[cantVartices];
    }
        @Override
        public void agregarVertice(int verticeId) {
            // TODO Auto-generated method stub
            mapeo[index]=verticeId;
            index++;

            //version 3
            vertices.add(verticeId);
        }

        @Override
        public void borrarVertice(int verticeId) {
            // TODO Auto-generated method stub
        }

        @Override
        public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
            // TODO Auto-generated method stub
            //version 3
            if (!existeArco(verticeId1, verticeId2)) {
                arcos.add(new Arco<T>(verticeId1, verticeId2, etiqueta));

            }

        }

        @Override
        public void borrarArco(int verticeId1, int verticeId2) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean contieneVertice(int verticeId) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean existeArco(int verticeId1, int verticeId2) {
            // TODO Auto-generated method stub
            //version 1
            //int indexVerticeid1= ;//buscar verticeId1 en el mapeo
            //int indexVerticeid2= ;//buscar verticeId1 en el mapeo

            //version 2
           /* int indexVerticeid1= mapeoHash.get(verticeId1) ;//buscar verticeId1 en el mapeo
            int indexVerticeid2= mapeoHash.get(verticeId2);//buscar verticeId1 en el mapeo
            return (matriz[indexVerticeId1][indexVerticeId2]!=null);       */
            //return (matriz[verticeId1][verticeId2]!=null);

            //version 3
            //recorro todos los arcos de mi grafo y si hay algun arcos entre esos 2 vertices return true
            //se le agrega complejidad pq en el peor de los casos debo recorrer toda mi lista
            //O(a) donde a es la cant de arcos de mi grafo
            boolean existe=false;
            Iterator<Arco<T>>it=arcos.iterator();
            while (!existe && it.hasNext()){
                Arco<T>arco=it.next();
                if(arco.getVerticeOrigen()==verticeId1 && arco.getVerticeDestino()==verticeId2){
                    existe=true;
                }
            }
            return existe;

            //version 4
            //busco en todos los vertices si esta el id1
            //si esta busco entre sus arcos uno que lo conecte con el vertice id2
            //complejidad O(V + a)
            Iterator<Vertice<T>>itV=vertices1.iterator();
            while(itV.hasNext() &&!existe){
                Vertice<T> vertice=itV.next();
                if (vertice.getVerticeId()==verticeId1){
                    Iterator<Arco<T>>itA=vertice.getArcos().iterator();
                    while (!existe && itA.hasNext()){
                        Arco<T> a=itA.next();
                        if(a.getVerticeDestino()==verticeId2){
                            existe=true;
                        }
                    }
                }
            }

            //version 5
            //el while anterior se transforma en un get, la complejidad es menor O(a)
            Vertice v= vertices2.get(verticeId1);
            if (v.getVerticeId()==verticeId1){
                Iterator<Arco<T>>itA=v.getArcos().iterator();
                while (!existe && itA.hasNext()){
                    Arco<T> a=itA.next();
                    if(a.getVerticeDestino()==verticeId2){
                        existe=true;
                    }
                }
            }



        }

        @Override
        public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int cantidadVertices() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int cantidadArcos() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public Iterator<Integer> obtenerVertices() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Iterator<Integer> obtenerAdyacentes(int verticeId) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Iterator<Arco<T>> obtenerArcos() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Iterator<Arco<T>> obtenerArcos(int verticeId) {
            // TODO Auto-generated method stub
            return null;
        }
}
