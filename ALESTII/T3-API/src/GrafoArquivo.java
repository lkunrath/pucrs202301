import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GrafoArquivo {

    public static GrafoValorado lerArquivoGrafo(String caminhoArquivo) {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
            int numVertices = Integer.parseInt(leitor.readLine().trim());
            int numArestas = Integer.parseInt(leitor.readLine().trim());
            
            GrafoValorado grafo = new GrafoValorado(numVertices);
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(" ");
                int v = Integer.parseInt(partes[0]);
                int w = Integer.parseInt(partes[1]);
                double peso = Double.parseDouble(partes[2]);
                grafo.adicionarAresta(v, w, peso);
            }
            
            leitor.close();
            System.out.println(grafo.toDot());
            return grafo;
        } catch (IOException e) {
            System.out.println("Erro de leitura do arquivo: " + e.getMessage());
        }

        return null;
    }

    public static boolean salvarArquivoGrafo(GrafoValorado g, String caminhoArquivo) {

        return false;
    }
}