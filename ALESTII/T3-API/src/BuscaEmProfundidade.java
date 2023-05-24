package aula24_grafos_valorado;

import aula24_grafos_valorado.GrafoValorado.Aresta;

public class BuscaEmProfundidade {
    private boolean[] visitados;
    private int[] antecessor;
    public int[] preordem;
    public int[] posordem;
   
    public BuscaEmProfundidade(GrafoValorado g, int verticeOrigem) {
        int numeroVertices = g.numeroVertices();
        visitados = new boolean[numeroVertices];
        antecessor = new int[numeroVertices];
        preordem = new int[numeroVertices];
        posordem = new int[numeroVertices];

        for (int i = 0; i < numeroVertices; i++) {
            visitados[i] = false;
            antecessor = -1;
        }
        profundidade(g, verticeOrigem);        
    }

    public void profundidade(GrafoValorado g, int verticeOrigem){
        visitados[verticeOrigem] = true;
        preordem[verticeOrigem] = verticeOrigem;

        for (Aresta a : g.getListaAdjacencia(verticeOrigem)) {
            int verticeAdjacente = a.v;

            if(!visitados[verticeAdjacente]){
                antecessor[verticeAdjacente] = verticeOrigem;
                profundidade(g, verticeAdjacente);
            }
        }
        posordem = verticeOrigem;
    }

    public void imprimirResultado() {
        for (int i = 0; i < visitados.length; i++) {
            System.out.println("BUSCA EM PROFUNDIDADE");
            System.out.println("=====================\n");
            System.out.printf("Vertice: %d\nVisitados: %b\nAntecessor: %d\nPré-Ordem: %d\nPós-Ordem: %d\n--------------");
        }
    }
}
