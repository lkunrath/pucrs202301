import java.util.Arrays;

public class Dijkstra {
    private class CaminhoMinimo {
        int vertice;
        int distancia;

        public CaminhoMinimo(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }

    public int[] distancia;
    private int[] antecessor;
    private FilaPrioridadeMinima filaDistanciasMinimas;
    private Grafo grafo;

    public Dijkstra(Grafo g, int verticeOrigem) {
        this.grafo = g;
        distancia = new int[g.getNumeroVertices()];
        antecessor = new int[g.getNumeroVertices()];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(antecessor, -1);

        filaDistanciasMinimas = new FilaPrioridadeMinima();
        filaDistanciasMinimas.enfileirar(verticeOrigem, 0);

        distancia[verticeOrigem] = 0;
        antecessor[verticeOrigem] = -1;

        while (!filaDistanciasMinimas.estaVazia()) {
            int verticeAtual = filaDistanciasMinimas.desenfileirar();
            for (int destino : g.adjacentes(verticeAtual)) {
                int peso = 1; // Peso das arestas é considerado 1 para esse caso

                if (distancia[verticeAtual] + peso < distancia[destino]) {
                    distancia[destino] = distancia[verticeAtual] + peso;
                    antecessor[destino] = verticeAtual;
                    if (filaDistanciasMinimas.existe(destino))
                        filaDistanciasMinimas.atualizarDistancia(destino, distancia[destino]);
                    else
                        filaDistanciasMinimas.enfileirar(destino, distancia[destino]);
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("vertice,distancia,");
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            System.out.println(i + "," + distancia[i] + "," + antecessor[i]);
        }
    }
}