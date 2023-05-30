import java.util.ArrayList;

public class GrafoValorado {

    public static class Aresta {
        public int v;
        public int w;
        public double peso;

        public Aresta(int v, int w, double peso) {
            this.v = v;
            this.w = w;
            this.peso = peso;
        }
    }

    private ArrayList<Aresta> listaAdjacencia[];
    private int numeroVertices;
    private int nome = 0;

    public GrafoValorado(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.listaAdjacencia = new ArrayList[this.numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            this.listaAdjacencia[i] = new ArrayList<Aresta>();
        }
    }

    public void adicionarAresta(int v, int w, double peso) {
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

    public ArrayList<Aresta> getListaAdjacencia(int vertice) {
        return listaAdjacencia[vertice];
    }

    public void removerAresta(int v, int w) {
        for (Aresta a : listaAdjacencia[v]) {
            if (a.w == w)
                listaAdjacencia[v].remove(a);
        }
        for (Aresta a : listaAdjacencia[w]) {
            if (a.v == v)
                listaAdjacencia[w].remove(a);
        }
    }

    public int grau(int vertice) {
        return listaAdjacencia[vertice].size();
    }

    public int numeroVertices() {
        return listaAdjacencia.length;
    }

    public int numeroArestas() {
        int numeroArestas = 0;
        for (int i = 0; i < listaAdjacencia.length - 1; i++) {
            numeroArestas += listaAdjacencia[i].size();
        }
        return numeroArestas;
    }

    public String getNome() {
        String str = "Grafo" + nome;
        nome = nome + 1;
        return str;
    }
}