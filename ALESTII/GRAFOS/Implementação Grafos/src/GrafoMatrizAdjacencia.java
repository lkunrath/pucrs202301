public class GrafoMatrizAdjacencia {
    private int numeroVertices;
    private int numeroArestas;
    private boolean[][] matrizAdjacencia;
    
    public GrafoMatrizAdjacencia(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.numeroArestas = 0;
        matrizAdjacencia = new boolean[numeroVertices][numeroArestas];
    }

    public void adicionarAresta(int v, int a){
        matrizAdjacencia[v][a] = true;
        matrizAdjacencia[a][v] = true;
        numeroArestas++;
    }
    
    public void removerAresta(int v, int a){
        matrizAdjacencia[v][a] = false;
        matrizAdjacencia[a][v] = false;
    }

    public boolean existeAresta(int v, int a){
        return matrizAdjacencia[v][a];
    }
}