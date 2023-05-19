import java.util.ArrayList;

public class Digrafo {
    private ArrayList<Integer> listaAdjacencia[];

    public int getNumeroVertices() {
        return numeroVertices;
    }

    private int numeroVertices;
    private int numeroArestas;

    public Digrafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        this.numeroArestas = 0;
        listaAdjacencia = new ArrayList[numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            listaAdjacencia[i] = new ArrayList<>();
        }
    }

    public void adicionarAresta(int v, int w) {
        this.listaAdjacencia[v].add(w);
        this.numeroArestas++;
    }

    public ArrayList<Integer> verticesAdjacentes(int vertice) {
        return this.listaAdjacencia[vertice];
    }

    public String toDot() {
        String resultado = "digraph G { " + System.lineSeparator();
        for (int i = 0; i < numeroVertices; i++) {
            resultado = resultado + "\t" + i + ";" + System.lineSeparator();
        }
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < listaAdjacencia[i].size(); j++) {
                resultado += "\t" + i + "->" + listaAdjacencia[i].get(j) + ";" + System.lineSeparator();
            }
        }
        resultado += "}";
        return resultado;
    }

    public static void main(String[] args) {
        Digrafo g = new Digrafo(7);
        g.adicionarAresta(0, 1);
        g.adicionarAresta(0, 3);
        g.adicionarAresta(0, 5);
        g.adicionarAresta(1, 2);
        g.adicionarAresta(2, 4);
        g.adicionarAresta(2, 5);
        g.adicionarAresta(3, 6);
        g.adicionarAresta(4, 5);
        g.adicionarAresta(4, 6);
        System.out.println(g.toDot());

        BuscaEmProfundidade busca = new BuscaEmProfundidade(g, 0);
        for (int i = 0; i < busca.getVisitados().length; i++)
            System.out.println(i + " - " + busca.getVisitados()[i]);
        System.out.println("");
        for (int i = 0; i < busca.getAntecessores().length; i++)
            System.out.println(i + " - " + busca.getAntecessores()[i]);
    }
}
