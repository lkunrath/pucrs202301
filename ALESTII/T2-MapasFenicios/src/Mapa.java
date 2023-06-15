import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mapa {
    public Grafo grafo;
    public Vertice[] vertices;
    public char[][] matrizMapa;
    private int numColunas;
    private int numLinhas;

    public Mapa(String caminhoArquivo) {
        lerArquivo(caminhoArquivo);
        carregarGrafo();
        conectarArestas();
        encontrarPortoInicial();
        // System.out.println(getGrafo().toDot());
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void carregarGrafo() {
        int numVertices = numLinhas * numColunas;
        grafo = new Grafo(numVertices);
        vertices = new Vertice[numVertices];

        int indiceVertices = 0;
        for (int lin = 0; lin < numLinhas; lin++) {
            for (int col = 0; col < numColunas; col++) {
                char caracter = matrizMapa[lin][col];
                vertices[indiceVertices] = new Vertice(indiceVertices, lin, col, caracter);

                if (Character.isDigit(caracter)) {
                    int porto = Character.getNumericValue(caracter);
                    int origem = obterIndiceVertice(lin, col);
                    int destino = obterIndiceVertice(lin, col);

                    // Conectar com vértice acima
                    if (lin > 0 && matrizMapa[lin - 1][col] != '*') {
                        destino = obterIndiceVertice(lin - 1, col);
                        int peso = Math.abs(porto - Character.getNumericValue(matrizMapa[lin - 1][col]));
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice abaixo
                    if (lin < numLinhas - 1 && matrizMapa[lin + 1][col] != '*') {
                        destino = obterIndiceVertice(lin + 1, col);
                        int peso = Math.abs(porto - Character.getNumericValue(matrizMapa[lin + 1][col]));
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice à esquerda
                    if (col > 0 && matrizMapa[lin][col - 1] != '*') {
                        destino = obterIndiceVertice(lin, col - 1);
                        int peso = Math.abs(porto - Character.getNumericValue(matrizMapa[lin][col - 1]));
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice à direita
                    if (col < numColunas - 1 && matrizMapa[lin][col + 1] != '*') {
                        destino = obterIndiceVertice(lin, col + 1);
                        int peso = Math.abs(porto - Character.getNumericValue(matrizMapa[lin][col + 1]));
                        grafo.adicionarAresta(origem, destino, peso);
                    }
                }

                indiceVertices++;
            }
        }

        /* for (Vertice vertice : vertices) {
            System.out.println(vertice);
        } */
    }

    public void conectarArestas() {
        for (int lin = 0; lin < numLinhas; lin++) {
            for (int col = 0; col < numColunas; col++) {
                char caracter = matrizMapa[lin][col];
                int origem = obterIndiceVertice(lin, col);

                if (caracter != '*') {
                    // Conectar com vértice acima
                    // Conectar com vértice acima
                    if (lin > 0 && matrizMapa[lin - 1][col] != '*') {
                        int destino = obterIndiceVertice(lin - 1, col);
                        int peso = Math.abs(caracter - matrizMapa[lin - 1][col]);
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice abaixo
                    if (lin < numLinhas - 1 && matrizMapa[lin + 1][col] != '*') {
                        int destino = obterIndiceVertice(lin + 1, col);
                        int peso = Math.abs(caracter - matrizMapa[lin + 1][col]);
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice à esquerda
                    if (col > 0 && matrizMapa[lin][col - 1] != '*') {
                        int destino = obterIndiceVertice(lin, col - 1);
                        int peso = Math.abs(caracter - matrizMapa[lin][col - 1]);
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                    // Conectar com vértice à direita
                    if (col < numColunas - 1 && matrizMapa[lin][col + 1] != '*') {
                        int destino = obterIndiceVertice(lin, col + 1);
                        int peso = Math.abs(caracter - matrizMapa[lin][col + 1]);
                        grafo.adicionarAresta(origem, destino, peso);
                    }

                }
            }
        }
    }

    public int obterIndiceVertice(int linha, int coluna) {
        return linha * numColunas + coluna;
    }

    private void lerArquivo(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            Scanner scanner = new Scanner(arquivo);

            numLinhas = scanner.nextInt();
            numColunas = scanner.nextInt();

            matrizMapa = new char[numLinhas][numColunas];
            scanner.nextLine(); // Consumir quebra de linha após os números

            for (int i = 0; i < numLinhas; i++) {
                String linha = scanner.nextLine();
                matrizMapa[i] = linha.toCharArray();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
            e.printStackTrace();
        }
    }

    private int encontrarPortoInicial() {
        for (int lin = 0; lin < numLinhas; lin++) {
            for (int col = 0; col < numColunas; col++) {
                char caracter = matrizMapa[lin][col];
                if (Character.isDigit(caracter)) {
                    return Character.getNumericValue(caracter);
                }
            }
        }
        return -1; // Caso nenhum porto seja encontrado
    }

    public int encontrarProximoPorto(int portoAtual) {
        int proximoPorto = -1;

        for (int lin = 0; lin < numLinhas; lin++) {
            for (int col = 0; col < numColunas; col++) {
                char caracter = matrizMapa[lin][col];
                if (Character.isDigit(caracter)) {
                    int numeroPorto = Character.getNumericValue(caracter);
                    if (numeroPorto == portoAtual) {
                        // Encontrou o porto atual, verificar o próximo porto disponível
                        proximoPorto = -1; // Valor padrão se nenhum porto for encontrado

                        // Verificar se o próximo porto está presente na matriz
                        if (col < numColunas - 1) {
                            char proximoCaracter = matrizMapa[lin][col + 1];
                            if (Character.isDigit(proximoCaracter)) {
                                proximoPorto = Character.getNumericValue(proximoCaracter);
                            }
                        }

                        // Caso não tenha encontrado na mesma linha, procurar nas linhas abaixo
                        if (proximoPorto == -1) {
                            for (int l = lin + 1; l < numLinhas; l++) {
                                for (int c = 0; c < numColunas; c++) {
                                    char proximoCaracter = matrizMapa[l][c];
                                    if (Character.isDigit(proximoCaracter)) {
                                        proximoPorto = Character.getNumericValue(proximoCaracter);
                                        break;
                                    }
                                }
                                if (proximoPorto != -1) {
                                    break;
                                }
                            }
                        }

                        // Se encontrou o próximo porto, encerra a busca
                        if (proximoPorto != -1) {
                            return proximoPorto;
                        }
                    }
                }
            }
        }

        return proximoPorto; // Retorna -1 se não encontrou o próximo porto
    }
}