package Entidades;

import java.util.ArrayList;

public class Macaquinho {
    private int[] infos;

    public Macaquinho(int[] infos) {
        this.infos = infos;
    }

    /*
     * Controla a quantidade de rodadas, faz com que cada macaco distribua todos
     * os seus côcos em cada rodada e faz as jogadas de acordo com o macaquinho
     * destino parar côcos pares e ímapres
     */
    public static void controle(ArrayList<Macaquinho> macaquinhos, int rodadas) {
        for (int i = 0; i <= rodadas; i++) {
            for (int j = 0; j < macaquinhos.size(); j++) {
                macaquinhos.get(macaquinhos.get(j).getInfos()[0]).infos[2] += macaquinhos.get(j).infos[2];
                macaquinhos.get(macaquinhos.get(j).getInfos()[1]).infos[3] += macaquinhos.get(j).infos[3];

                macaquinhos.get(j).infos[2] = 0;
                macaquinhos.get(j).infos[3] = 0;

            }
        }
    }

    public int[] getInfos() {
        return infos;
    }
}