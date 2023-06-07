package ALESTII.GRAFOS.Dijksta;

import java.util.LinkedList;
import java.util.Queue;

public class CaminhamentoLargura {
    public boolean[] percorrido;
    public int[] antecessor;
    public int[] distancia;

    public CaminhamentoLargura(GrafoDirigidoValorado g, int origem) {
        percorrido = new boolean[g.getNumeroVertices()];
        antecessor = new int[g.getNumeroVertices()];
        distancia = new int[g.getNumeroVertices()];
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            percorrido[i] = false;
            antecessor[i] = -1;
            distancia[i] = 0;
        }
        Queue<Integer> fila = new LinkedList<>();
        fila.add(origem);
        while (!fila.isEmpty()) {
            Integer v = fila.poll();
            percorrido[v] = true;
            for (GrafoDirigidoValorado.ArestaDirigida aresta : g.getListaAdjacencia(v)) {
                int destino = aresta.destino;
                if (!percorrido[destino]) {
                    percorrido[destino] = true;
                    antecessor[destino] = v;
                    distancia[destino] = distancia[v] + aresta.peso;
                    fila.add(destino);
                }
            }
        }
        imprimirResultado(g);
    }

    public void imprimirResultado(GrafoDirigidoValorado g) {
        System.out.println("vertice,antecessor,percorrido,distancia");
        for (int i = 0; i < g.getNumeroVertices(); i++) {
            System.out.println(i + "," + antecessor[i] + "," + percorrido[i] + "," + distancia[i]);
        }
    }
}
