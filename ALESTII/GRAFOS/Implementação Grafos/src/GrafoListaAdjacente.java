import java.util.ArrayList;

public class GrafoListaAdjacente {
    private int numeroVertices;
    private int numeroArestas;
    private ArrayList<Integer>[] listaAdjacencia;

    public GrafoListaAdjacente(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.numeroArestas = 0;

        listaAdjacencia = new ArrayList[numeroVertices];
        for (int i = 0; i < this.numeroVertices; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
    }

    public void adicionarAresta(int v, int a) {
        listaAdjacencia[v].add(a);
        listaAdjacencia[a].add(v);
        numeroArestas++;
    }

    public boolean existeAresta(int v, int a) {
        boolean va = listaAdjacencia[v].indexOf(a) >= 0;
        boolean av = listaAdjacencia[a].indexOf(v) >= 0;
        return va || av;
    }

    public void removerAresta(int v, int a) {
        int va = listaAdjacencia[v].indexOf(a);
        listaAdjacencia[v].remove(va);
        int av = listaAdjacencia[a].indexOf(v);
        listaAdjacencia[a].remove(av);
    }

    public ArrayList<Integer> verticesAdjacentes(int v) {
        return listaAdjacencia[v];
    }

    public void buscarEmProfundidade(int origem) {
        boolean[] visitados = new boolean[this.numeroVertices];
        buscarEmProfundidadeRecursivamente(origem, visitados);
    }

    private void buscarEmProfundidadeRecursivamente(int vertice, boolean[] visitados) {
        visitados[vertice] = true;
        ArrayList<Integer> adjacentes = this.verticesAdjacentes(vertice);
        for (int vizinho : adjacentes) {
            if (!visitados[vizinho]) {
                System.out.println("Vou visitar o " + vizinho);
                buscarEmProfundidadeRecursivamente(vertice, visitados);
            } else {
                System.out.println("Não vou visitar o " + vizinho + " pois ele já foi visitado");
            }
        }
    }

    public String toDot() {
        String resultado = "graph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado += "\t" + i + "--" + listaAdjacencia[i].get(j) + ";" + System.lineSeparator();
            }
        }
        resultado += "}";
        return resultado;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < this.numeroVertices; i++) {
            ret += i + " { ";
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                ret += listaAdjacencia[i].get(j) + " | ";
            }
            ret = ret + " } " + System.lineSeparator();
        }
        return ret;
    }
}