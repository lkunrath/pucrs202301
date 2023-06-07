package ALESTII.GRAFOS.Dijksta;

import java.util.ArrayList;

public class GrafoDirigidoValorado {
    class ArestaDirigida {
        public int origem;
        public int destino;
        public int peso;
        public ArestaDirigida(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }
    private ArrayList<ArestaDirigida>[] listaAdjacencia;
    private int numeroVertices;
    public GrafoDirigidoValorado(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        listaAdjacencia = new ArrayList[this.numeroVertices];
        for (int i = 0; i < this.numeroVertices ; i++) listaAdjacencia[i] = new ArrayList<>();
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        ArestaDirigida a = new ArestaDirigida(origem, destino, peso);
        listaAdjacencia[origem].add(a);
    }
    public void removerAresta(int origem, int destino) {
        ArrayList<ArestaDirigida> listaArestas = listaAdjacencia[origem];
        for (ArestaDirigida a:listaArestas) {
            if(a.destino==destino)  {
                listaArestas.remove(a);
                return;
            }
        }
    }
    public int getNumeroVertices() { return this.numeroVertices;}
    public ArrayList<ArestaDirigida> getListaAdjacencia(int vertice) {
        return listaAdjacencia[vertice];
    }
    public ArestaDirigida getArestaMenorPeso(int vertice) {
        ArrayList<ArestaDirigida> arestas = getListaAdjacencia(vertice);
        int menorPeso = Integer.MAX_VALUE;
        ArestaDirigida arestaMenorPeso = arestas.get(0);
        for (ArestaDirigida a:arestas) {
            if(a.peso<menorPeso) arestaMenorPeso = a;
        }
        return arestaMenorPeso;
    }
    public String toDot() {
        String resultado = "digraph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) resultado += "\t" + listaAdjacencia[i].get(j).origem + "->" + listaAdjacencia[i].get(j).destino + "  [label=" + listaAdjacencia[i].get(j).peso + "]" + ";" + System.lineSeparator();
        }
        resultado += "}";
        return resultado;
    }
}
