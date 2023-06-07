package ALESTII.GRAFOS.Dijksta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra {
    private class CaminhoMinimo {
        int vertice;
        int distancia;
        public CaminhoMinimo(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }
    private int[] distancia;
    private int[] antecessor;
    private FilaPrioridadeMinima filaDistanciasMinimas;
    private GrafoDirigidoValorado grafo;
    public Dijkstra(GrafoDirigidoValorado g, int verticeOrigem) {
        this.grafo = g;
        distancia = new int[g.getNumeroVertices()];
        antecessor = new int[g.getNumeroVertices()];
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            distancia[i] = Integer.MAX_VALUE;
            antecessor[i] = -1;
        }

        filaDistanciasMinimas = new FilaPrioridadeMinima();
        filaDistanciasMinimas.enfileirar(verticeOrigem, 0);

        distancia[verticeOrigem] = 0;
        antecessor[verticeOrigem] = -1;

        while(!filaDistanciasMinimas.estaVazia()) {
            int verticeAtual = filaDistanciasMinimas.desenfileirar();
            for (GrafoDirigidoValorado.ArestaDirigida a:g.getListaAdjacencia(verticeAtual)) {
                if(distancia[verticeAtual] + a.peso < distancia[a.destino]) {
                    distancia[a.destino] = distancia[verticeAtual] + a.peso;
                    antecessor[a.destino] = verticeAtual;
                    if(filaDistanciasMinimas.existe(a.destino)) filaDistanciasMinimas.atualizarDistanca(a.destino, distancia[a.destino]);
                    else filaDistanciasMinimas.enfileirar(a.destino, distancia[a.destino]);
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("vertice,distancia,antecessor");
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            System.out.println(i + "," + distancia[i] + "," + antecessor[i]);
        }
    }

}