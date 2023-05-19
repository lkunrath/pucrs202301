package Entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private static String path;
    private static int qtdCocos;

    /**
     * Monta o jogo lendo o arquivo selecionado e retorna o estado
     * final da lista de macaquinhos
     */
    public static ArrayList<Macaquinho> montaJogo() throws FileNotFoundException {
        ArrayList<Macaquinho> macaquinhos = new ArrayList<>();

        File file = new File(getPath());
        try (Scanner sc = new Scanner(file)) {

            /*
             * Salva apenas os números da primeira linha do arquivo,
             * correspondente às rodadas
             */
            int rodadas = Integer.parseInt(sc.nextLine().replaceAll("\\D+", "")) - 1;
            // Testa se ainda existem linhas à ler
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                int[] infos = new int[4];
                int countPar = 0;
                int countImpar = 0;
                /*
                 * Lê cada linha do arquivo e a separa ao encontrar um espaço(" ") no texto,
                 * armazenando cada separação em uma posição do vetor
                 */
                String[] linhasVet = linha.split(" ");

                /*
                 * A partir do décimo primeiro elemento da linha, onde começam
                 * as infosrmações dos côcos. Os côcos são filtrados por par ou ímpar
                 * e o total de cada macaquinho é colocado no array
                 */
                for (int i = 11; i < linhasVet.length; i++) {
                    if (Long.parseLong(linhasVet[i]) % 2 == 0) {
                        countPar++;

                    } else if (Long.parseLong(linhasVet[i]) % 2 != 0) {
                        countImpar++;
                    }

                }
                infos[0] = Integer.parseInt(linhasVet[4]); // Par destino
                infos[1] = Integer.parseInt(linhasVet[7]); // Impar destino
                infos[2] = countPar; // Quantidadde de côcos pares
                infos[3] = countImpar; // Quantidade de côcos ímpares

                /*
                 * Adiciona um Macaquinho à lista de macaquinhos, passando o macaquinho a ser
                 * enviado os cocos ímpares, o macaquinho à ser enviado os côcos pares e as
                 * quantidades de cocos pares e impares
                 */
                Macaquinho m = new Macaquinho(infos);
                macaquinhos.add(m);
            }
            Macaquinho.controle(macaquinhos, rodadas);
        }
        return macaquinhos;
    }

    /*
     * Faz uma variação de BubleSort para encontrar o índice do macaquinho vencedor,
     * comparando a quantidade de côcos de cada macaquinho com o do próximo
     */
    public static int Ganhador(ArrayList<Macaquinho> listaDeMacaquinhos) {
        Macaquinho aux = listaDeMacaquinhos.get(0);
        int vencedor = 0;

        for (Macaquinho m : listaDeMacaquinhos) {
            if (totalCocos(m) > totalCocos(aux)) {
                aux = m;
                vencedor = listaDeMacaquinhos.indexOf(m);
                setQtdCocos(totalCocos(m));
            }
        }
        return vencedor;
    }

    public static int totalCocos(Macaquinho m) {
        int totalCoco = 0;
        totalCoco = m.getInfos()[2] + m.getInfos()[3];
        return totalCoco;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Jogo.path = path;
    }

    public static int getQtdCocos() {
        return qtdCocos;
    }

    public static void setQtdCocos(int qtdCocos) {
        Jogo.qtdCocos = qtdCocos;
    }
}