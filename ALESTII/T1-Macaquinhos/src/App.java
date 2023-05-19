import java.util.Scanner;

import Entidades.Jogo;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
       
        System.out.print("\n\nDigite o nome completo do arquivo .txt que você deseja testar: ");
        String path = sc.next();
        Jogo.setPath(path);

        
        System.out.println("Arquivo testado: " + Jogo.getPath());
        System.out.println("\nJogando...");
        long start = System. currentTimeMillis();
        System.out.println("\n - O macaquinho vencedor é o número " + Jogo.Ganhador(Jogo.montaJogo()) + ", com " + Jogo.getQtdCocos() + " cocos");
        long elapsed = System. currentTimeMillis() - start; 
        
        if(elapsed < 1000){
            System.out.println(" - O jogo foi executado em " + elapsed + " milisegundos\n\n\n\n");
        }else{
            double aux = elapsed/100;
            System.out.printf(" - O jogo foi executado em %.1f segundos\n\n\n\n" ,aux /10);
        }
        sc.close();
    }
}
