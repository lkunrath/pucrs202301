public class Dijkstra {
    public int[] antecessor;
    public int[] distancia;
    public boolean[] percorrido;
    private Grafo grafo;

    public Dijkstra(Grafo g, int origem) {
        this.grafo = g;
        antecessor = new int[grafo.getNumeroVertices()];
        distancia = new int[grafo.getNumeroVertices()];
        percorrido = new boolean[grafo.getNumeroVertices()];

        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            antecessor[i] = -1;
            distancia[i] = Integer.MAX_VALUE;
            percorrido[i] = false;
        }

        FilaPrioridadeMinima filaMin = new FilaPrioridadeMinima();
        filaMin.enfileirar(origem, 0);
        distancia[origem] = 0;

        while (!filaMin.estaVazia()) {
            int vertice = filaMin.desenfileirar();
            percorrido[vertice] = true;
            for (Integer destino : grafo.adjacentes(vertice)) {
                int distanciaDestino = distancia[vertice] + 1;
                // System.out.println(distanciaDestino);
                if (distanciaDestino < distancia[destino]) {
                    antecessor[destino] = vertice;
                    distancia[destino] = distanciaDestino;
                    if (!filaMin.existe(destino))
                        filaMin.enfileirar(destino, distanciaDestino);
                    else
                        filaMin.atualizarDistancia(destino, distanciaDestino);
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("vertice,distancia,antecessor,percorrido");
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            System.out.println(i + "," + distancia[i] + "," + antecessor[i] + "," + percorrido[i]);
        }
    }
}