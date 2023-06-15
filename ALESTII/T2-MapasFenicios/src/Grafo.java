import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {
    private int numeroVertices;
    private int numeroArestas;
    private ArrayList<Integer>[] listaAdjacencia;
    private static final int MOVIMENTOS_POSSIVEIS = 4;
    private static final int[] LINHAS = { -1, 0, 1, 0 }; // Movimentos na vertical
    private static final int[] COLUNAS = { 0, 1, 0, -1 }; // Movimentos na horizontal

    public Grafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.numeroArestas = 0;

        listaAdjacencia = new ArrayList[numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
    }

    public int getNumeroVertices() {
        return numeroVertices;
    }

    public ArrayList<Integer> adjacentes(int v) {
        return listaAdjacencia[v];
    }

    public void adicionarAresta(int vertice, int aresta) {
        listaAdjacencia[vertice].add(aresta);
        listaAdjacencia[aresta].add(vertice);
        numeroArestas++;
    }

    public boolean existeAresta(int vertice, int aresta) {
        boolean va = listaAdjacencia[vertice].contains(aresta);
        boolean av = listaAdjacencia[aresta].contains(vertice);
        return va || av;
    }

    public void removerAresta(int vertice, int aresta) {
        listaAdjacencia[vertice].remove(Integer.valueOf(aresta));
        listaAdjacencia[aresta].remove(Integer.valueOf(vertice));
    }

    public List<Integer> verticesAdjacentes(int vertice) {
        return listaAdjacencia[vertice];
    }

    public void buscarEmProfundidade(int origem) {
        boolean[] visitados = new boolean[this.numeroVertices];
        buscarEmProfundidadeRecursivamente(origem, visitados);
    }

    private void buscarEmProfundidadeRecursivamente(int vertice, boolean[] visitados) {
        visitados[vertice] = true;
        List<Integer> adjacentes = this.verticesAdjacentes(vertice);
        for (int vizinho : adjacentes) {
            if (!visitados[vizinho]) {
                System.out.println("Vou visitar o " + vizinho);
                buscarEmProfundidadeRecursivamente(vizinho, visitados);
            } else {
                System.out.println("Não vou visitar o " + vizinho + " pois ele já foi visitado");
            }
        }
    }

    public int menorCaminho(int origem, int destino) {
        System.out.println("Origem: " + origem + "   Destino: " + destino);
        int[] distancias = new int[numeroVertices];
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

            for (int i = 0; i < MOVIMENTOS_POSSIVEIS; i++) {
                int linhaVizinha = u / COLUNAS.length + LINHAS[i];
                int colunaVizinha = u % COLUNAS.length + COLUNAS[i];
                int vizinho = linhaVizinha * COLUNAS.length + colunaVizinha;

                // Verificar se o vizinho é válido e se existe uma aresta para ele
                if (linhaVizinha >= 0 && linhaVizinha < LINHAS.length &&
                        colunaVizinha >= 0 && colunaVizinha < COLUNAS.length &&
                        existeAresta(u, vizinho)) {

                    int pesoAresta = 1; // Peso das arestas é considerado 1 para esse caso

                    if (distancias[vizinho] > distancias[u] + pesoAresta) {
                        distancias[vizinho] = distancias[u] + pesoAresta;
                        filaPrioridade.add(new VerticeDistancia(vizinho, distancias[vizinho]));
                    }
                }
            }
        }

        return distancias[destino];
    }

    public int calcularUnidadesCombustivel(int portoInicial) {
        int quantidadeCombustivel = 0;

        for (int porto = 0; porto < numeroVertices; porto++) {
            if (porto != portoInicial) {
                int caminhoAtual = menorCaminho(portoInicial, porto);
                quantidadeCombustivel += (2 * caminhoAtual);
            }
        }

        // Considerando 1 unidade de combustível por movimento
        quantidadeCombustivel *= 2;

        return quantidadeCombustivel;
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

    public String toDot() {
        StringBuilder resultado = new StringBuilder("graph G {").append(System.lineSeparator());
        for (int i = 0; i < numeroVertices; i++) {
            resultado.append("\t").append(i).append(";").append(System.lineSeparator());
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado.append("\t").append(i).append("--").append(listaAdjacencia[i].get(j)).append(";")
                        .append(System.lineSeparator());
            }
        }
        resultado.append("}");
        return resultado.toString();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < this.numeroVertices; i++) {
            ret.append(i).append(" { ");
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                ret.append(listaAdjacencia[i].get(j)).append(" | ");
            }
            ret.append(" } ").append(System.lineSeparator());
        }
        return ret.toString();
    }
}
