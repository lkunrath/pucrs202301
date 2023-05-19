package atividade_de_aula;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        nomes.add("leonardo");
        nomes.add("mariana");
        nomes.add("alexandre");
        nomes.add("rafaella");
        nomes.add("joana");
        nomes.add("tales");
        nomes.add("anita");
        nomes.add("lucas");
        nomes.add("teresa");
        nomes.add("tito");

        Grafo grafo = new Grafo(nomes.size());
        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(0, 3);
        grafo.adicionarAresta(4, 5);
        grafo.adicionarAresta(4, 6);
        grafo.adicionarAresta(4, 7);
        grafo.adicionarAresta(5, 6);
        grafo.adicionarAresta(8, 9);
        grafo.adicionarAresta(7, 1);
        grafo.adicionarAresta(7, 2);
        grafo.adicionarAresta(3, 9);

        List<List<Integer>> conexoes = grafo.converterNomesParaListaInteiros(nomes);

        /*
         * System.out.println(conexoes);
         * 
         * for (int i = 0; i < conexoes.size(); i++) {
         * String nome = nomes.get(i);
         * List<Integer> conexoesPessoa = conexoes.get(i);
         * System.out.println(nome + " está conectado com: ");
         * for (int j : conexoesPessoa) {
         * String nomeConexao = nomes.get(j);
         * System.out.println("- " + nomeConexao);
         * }
         * System.out.println();
         * }
         */

        List<String> pessoasComMaisAmigos = grafo.pessoasComMaisAmigos(nomes);
        System.out.println("Pessoas com mais amigos:");
        for (String pessoa : pessoasComMaisAmigos) {
            System.out.println("- " + pessoa);
        }
        System.out.println();

        List<String> pessoasComMenosAmigos = grafo.pessoasComMenosAmigos(nomes);
        System.out.println("Pessoas com menos amigos:");
        for (String pessoa : pessoasComMenosAmigos) {
            System.out.println("- " + pessoa);
        }
        System.out.println();

        int pessoaX = 0; // Exemplo: pessoa X é a primeira pessoa na lista
        grafo.listarConexoesComDistanciaX(pessoaX, nomes);
    }
}