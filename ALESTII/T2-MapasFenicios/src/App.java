public class App {
    public static void main(String[] args) {
        Mapa mapa = new Mapa("Mapas/mapa0.txt");
        Dijkstra dij = new Dijkstra(mapa.grafo, 2);
        //int quantidadeCombustivel = mapa.getGrafo().calcularUnidadesCombustivel(1);
        //System.out.println("Quantidade de combustível necessária: " + quantidadeCombustivel + " unidades");
        dij.imprimirResultado();
        int distancia = dij.distancia[0];
        /* for (int i = 0; i < dij.distancia.length; i++) {
            System.out.println(i + "," + dij.distancia[i]);
        } */
        //System.out.println(distancia); 
    }
}
