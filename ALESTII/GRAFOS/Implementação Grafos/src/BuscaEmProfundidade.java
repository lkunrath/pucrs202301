import java.util.ArrayList;

public class BuscaEmProfundidade {
    private boolean[] visitados;
    private int[] antecessor;

    public BuscaEmProfundidade(Digrafo g, int verticeOrigem) {
        this.visitados = new boolean[g.getNumeroVertices()];
        this.antecessor = new int[g.getNumeroVertices()];
        for (int i = 0; i < antecessor.length; i++) {
            antecessor[i] = -1;
        }
        executarBuscaRecursiva(g, verticeOrigem);
    }

    private void executarBuscaRecursiva(Digrafo g, int vertice) {
        visitados[vertice] = true;
        ArrayList<Integer> adjacentes = g.verticesAdjacentes(vertice);
        for (int vizinho : adjacentes) {
            if (!visitados[vizinho]) {
                antecessor[vizinho] = vertice;
                executarBuscaRecursiva(g, vizinho);
            }
        }
    }

    public boolean[] getVisitados() {
        boolean[] retorno = new boolean[visitados.length];
        for (int i = 0; i < visitados.length; i++) {
            retorno[i] = visitados[i];
        }
        return retorno;
    }

    public int[] getAntecessores() {
        int[] retorno = new int[visitados.length];
        for (int i = 0; i < visitados.length; i++) {
            retorno[i] = antecessor[i];
        }
        return retorno;
    }
}