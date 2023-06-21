import java.util.LinkedList;
import java.util.Queue;

public class BuscaEmLargura {
    private boolean[] visitados;
    private int[] antecessor;
    private int[] distancia;

    public BuscaEmLargura(GrafoValorado g, int verticeOrigem) {
        int numeroVertices = g.numeroVertices();
        visitados = new boolean[numeroVertices];
        antecessor = new int[numeroVertices];
        distancia = new int[numeroVertices];

        for (int i = 0; i < numeroVertices; i++) {
            visitados[i] = false;
            antecessor[i] = -1;
            distancia[i] = Integer.MAX_VALUE;
        }
        largura(g, verticeOrigem);
    }

    private void largura(GrafoValorado g, int vertice) {
        Queue<Integer> fila = new LinkedList<>();
        visitados[vertice] = true;
        distancia[vertice] = 0;
        fila.offer(vertice);

        while (!fila.isEmpty()) {
            int v = fila.poll();

            for (GrafoValorado.Aresta aresta : g.getListaAdjacencia(v)) {
                int w = aresta.w;

                if (!visitados[w]) {
                    visitados[w] = true;
                    antecessor[w] = v;
                    distancia[w] = distancia[v] + 1;
                    fila.offer(w);
                }
            }
        }
    }

    public void imprimirResultado() {
        String str = "=============================";
        System.out.println(str + "\nArray de visitados:");
        for (int i = 0; i < visitados.length; i++) {
            System.out.println("Vertice " + i + ": " + visitados[i]);
        }

        System.out.println(str + "\nArray de antecessores:");
        for (int i = 0; i < antecessor.length; i++) {
            System.out.println("Vertice " + i + ": " + antecessor[i]);
        }

        System.out.println(str + "\nArray de distancias:");
        for (int i = 0; i < distancia.length; i++) {
            System.out.println("Vertice " + i + ": " + distancia[i]);
        }
    }
}
