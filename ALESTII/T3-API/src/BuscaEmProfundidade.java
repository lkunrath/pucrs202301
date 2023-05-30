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
            antecessor[i] = -1;
        }
        profundidade(g, verticeOrigem);
    }

    public void profundidade(GrafoValorado g, int verticeOrigem) {
        visitados[verticeOrigem] = true;
        preordem[verticeOrigem] = verticeOrigem;

        for (GrafoValorado.Aresta a : g.getListaAdjacencia(verticeOrigem)) {
            int verticeAdjacente = a.v;

            if (!visitados[verticeAdjacente]) {
                antecessor[verticeAdjacente] = verticeOrigem;
                profundidade(g, verticeAdjacente);
            }
        }
        posordem[verticeOrigem] = verticeOrigem;
    }

    public void imprimirResultado() {
        for (int i = 0; i < visitados.length; i++) {
            System.out.println("\nBUSCA EM PROFUNDIDADE");
            System.out.println("=====================");
            System.out.printf(
                    "Vertice: %d\nVisitados: %b\nAntecessor: %d\nPré-Ordem: %d\nPós-Ordem: %d\n--------------\n", i,
                    visitados[i], antecessor[i], preordem[i], posordem[i]);
        }
    }
}
