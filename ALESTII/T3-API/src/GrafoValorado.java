package aula24_grafos_valorado;

import java.util.ArrayList;

public class GrafoValorado {

    class Aresta {
        public int v;
        public int w;
        public int peso;

        public Aresta(int v, int w, int peso) {
            this.v = v;
            this.w = w;
            this.peso = peso;
        }
    }

    private ArrayList<Aresta> listaAdjacencia[];
    private int numeroVertices;

    public GrafoValorado(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.listaAdjacencia = new ArrayList[this.numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            this.listaAdjacencia[i] = new ArrayList<Aresta>();
        }
    }

    public void adicionarAresta(int v, int w, int peso) {
        Aresta e1 = new Aresta(v, w, peso);
        listaAdjacencia[v].add(e1);

        Aresta e2 = new Aresta(w, v, peso);
        listaAdjacencia[w].add(e2);
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        boolean[][] jaImpresso = new boolean[numeroVertices][numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                if (!jaImpresso[i][j]) {
                    resultado += "\t" + listaAdjacencia[i].get(j).v + "--" + listaAdjacencia[i].get(j).w + "  [label="
                            + listaAdjacencia[i].get(j).peso + "]" + ";" + System.lineSeparator();
                    jaImpresso[listaAdjacencia[i].get(j).v][listaAdjacencia[i].get(j).w] = true;
                    jaImpresso[listaAdjacencia[i].get(j).w][listaAdjacencia[i].get(j).v] = true;
                }
            }
        }
        resultado += "}";
        return resultado;
    }

    public static void main(String[] args) {
        GrafoValorado g = new GrafoValorado(4);
        g.adicionarAresta(0, 1, 33);
        g.adicionarAresta(0, 2, 10);
        g.adicionarAresta(1, 2, 99);
        g.adicionarAresta(0, 3, 200);

        System.out.println(g.toDot());
    }

    public void removerAresta(int v, int w) {
          for (Aresta a : listaAdjacencia[v]){
            if(a.w == w)
            listaAdjacencia[v].remove(a);
        }
        for (Aresta a : listaAdjacencia[w]){
            if(a.v == v)
            listaAdjacencia[w].remove(a);
        }
    }

    public int grau(int vertice) {
        return listaAdjacencia[v].size();
    }

    public int numeroVertices() {
        return listaAdjacencia.length;
    }

    public int numeroArestas() {
        int numeroArestas = 0;
        for (int i = 0; i < listaAdjacencia.length-1; i++) {
            numeroArestas += listaAdjacencia[i].size();
        }
        return numeroArestas;
    }
}