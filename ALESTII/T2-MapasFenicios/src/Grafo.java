import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {
    private int numVertices;
    private int numArestas;
    private ArrayList<Integer>[] listaAdjacencia;
    private int[][] pesosArestas;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.listaAdjacencia = new ArrayList[numVertices];
        this.pesosArestas = new int[numVertices][numVertices];
        this.numArestas = 0;

        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
    }

    public int getNumeroVertices() {
        return numVertices;
    }

    public ArrayList<Integer> adjacentes(int v) {
        return listaAdjacencia[v];
    }

    public void adicionarAresta(int vertice, int aresta, int peso) {
        listaAdjacencia[vertice].add(aresta);
        listaAdjacencia[aresta].add(vertice);
        pesosArestas[vertice][aresta] = peso;
        pesosArestas[aresta][vertice] = peso;
        numArestas++;
    }

    public boolean existeAresta(int vertice, int aresta) {
        return listaAdjacencia[vertice].contains(aresta);
    }

    public int obterPesoAresta(int vertice, int aresta) {
        return pesosArestas[vertice][aresta];
    }

    public List<Integer> verticesAdjacentes(int vertice) {
        return listaAdjacencia[vertice];
    }

    public int menorCaminho(int origem, int destino) {
        System.out.println("Origem: " + origem + "   Destino: " + destino);
        int[] distancias = new int[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;

        PriorityQueue<VerticeDistancia> filaPrioridade = new PriorityQueue<>();
        filaPrioridade.add(new VerticeDistancia(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            VerticeDistancia verticeAtual = filaPrioridade.poll();
            int u = verticeAtual.vertice;
            int distanciaAtual = verticeAtual.distancia;

            // Verificar se já foi encontrado um caminho mais curto para o vértice atual
            if (distanciaAtual > distancias[u]) {
                continue;
            }

            for (int vizinho : listaAdjacencia[u]) {
                int pesoAresta = pesosArestas[u][vizinho];

                if (distancias[vizinho] > distancias[u] + pesoAresta) {
                    distancias[vizinho] = distancias[u] + pesoAresta;
                    filaPrioridade.add(new VerticeDistancia(vizinho, distancias[vizinho]));
                }
            }
        }

        return distancias[destino];
    }

    public int calcularTotalMovimentos() {
        int quantidadeMovimentos = 0;
        int portoInicial = encontrarPortoInicial();

        int portoAtual = portoInicial;
        while (true) {
            int proximoPorto = encontrarProximoPorto(portoAtual);
            if (proximoPorto == -1) {
                break;
            }

            quantidadeMovimentos += menorCaminho(portoAtual, proximoPorto);
            portoAtual = proximoPorto;
        }

        quantidadeMovimentos += menorCaminho(portoAtual, portoInicial);

        return quantidadeMovimentos;
    }

    private int encontrarPortoInicial() {
        for (int porto = 0; porto < numVertices; porto++) {
            if (!adjacentes(porto).isEmpty()) {
                return porto;
            }
        }
        return -1; 
    }

    private int encontrarProximoPorto(int portoAtual) {
        for (int porto = portoAtual + 1; porto < numVertices; porto++) {
            if (adjacentes(porto).size() > 1) {
                return porto;
            }
        }
        return -1; 
    }

    private class VerticeDistancia implements Comparable<VerticeDistancia> {
        int vertice;
        int distancia;

        public VerticeDistancia(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(VerticeDistancia o) {
            return Integer.compare(distancia, o.distancia);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < this.numVertices; i++) {
            ret.append(i).append(" { ");
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                ret.append(listaAdjacencia[i].get(j)).append(" | ");
            }
            ret.append(" } ").append(System.lineSeparator());
        }
        return ret.toString();
    }
}
