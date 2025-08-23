import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppStreaming {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Midia> catalogo = new ArrayList<>();
        int escolha;

        do {
            System.out.println("\n(1) Adicionar novo Filme.");
            System.out.println("(2) Adicionar nova Série.");
            System.out.println("(3) Listar todas as mídias.");
            System.out.println("(4) Sair.");
            System.out.print("Opção: ");
            escolha=entrada.nextInt();
            switch (escolha) {
                case 1:
                    entrada.nextLine();
                    System.out.print("Título do filme: ");
                    String tituloFilme = entrada.nextLine();
                    long duracaoFilme = verDuracao(entrada, "Duração (min): ");
                    catalogo.add(new Filme(tituloFilme, duracaoFilme));
                    break;

                case 2:
                    entrada.nextLine();
                    System.out.print("Título da série: ");
                    String tituloSerie = entrada.nextLine();
                    Serie serie = new Serie(tituloSerie);

                    for (int numeroTemp = 1; numeroTemp <= 2; numeroTemp++) {
                        Temporada temporada = new Temporada(numeroTemp);

                        for (int numeroEp = 1; numeroEp <= 2; numeroEp++) {
                            System.out.print("Título do episódio " + numeroEp + " (T" + numeroTemp + "): ");
                            String tituloEp = entrada.nextLine();
                            long duracaoEp = verDuracao(entrada, "Duração (min): ");
                            temporada.adicionar(new Episodio(tituloEp, duracaoEp));
                        }

                        serie.adicionar(temporada);
                    }

                    catalogo.add(serie);
                    break;

                case 3:
                    entrada.nextLine();
                    if (catalogo.isEmpty()) {
                        System.out.println("Nenhuma mídia cadastrada.");
                    } else {
                        for (Midia midia : catalogo) {
                            System.out.println(midia.info());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Encerrando...");
                    break;

                default: 
                        System.out.println("Opção inválida.");
            }
        } while (escolha != 4);

        entrada.close();
    }

    private static long verDuracao(Scanner entrada, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Long.parseLong(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, digite um número.");
            }
        }
    }
}
