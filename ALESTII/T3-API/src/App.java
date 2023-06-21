public class App {
  public static void main(String[] args) throws Exception {
    GrafoValorado g = new GrafoValorado(4);
    g.adicionarAresta(0, 1, 33);
    g.adicionarAresta(0, 2, 10);
    g.adicionarAresta(1, 2, 99);
    g.adicionarAresta(0, 3, 200);

    String caminhoSalvaArquivo = "C:/Users/lkunr/OneDrive - PUCRS - BR/3º Semestre/pucrs202301/ALESTII/T3-API/saved_files/" + g.getNome();
    
    GrafoArquivo.salvarArquivoGrafo(g, caminhoSalvaArquivo);
    
    //String caminhoLeArquivo = "read_files/grafo.txt";

    //GrafoArquivo.lerArquivoGrafo(caminhoLeArquivo);

    //BuscaEmProfundidade buscaProfundidade = new BuscaEmProfundidade(g, 0);
    //buscaProfundidade.imprimirResultado();

    //BuscaEmLargura buscaLargura = new BuscaEmLargura(g, 0);
    //buscaLargura.imprimirResultado();
  }
}