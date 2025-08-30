import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    
    static List<Produto> pesquisar(List<Produto> produtos, String valor, CriterioBusca criterio) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (criterio.testar(p, valor)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    static void menuPrincipal(List<Produto> produtos) {
        boolean sair = false;

        while (!sair) {
            System.out.println("\n(1) Listar produtos");
            System.out.println("(2) Pesquisar descrição");
            System.out.println("(3) Pesquisar marca");
            System.out.println("(4) Pesquisar pelo preço máximo");
            System.out.println("(5) Pesquisar pelo preço mínimo");
            System.out.println("(0) Sair");
        
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "0":
                    sair = true;
                    break;
                case "1":
                    System.out.println("\n\nLista de produtos: ");
                    for (Produto p: produtos) {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    System.out.print("Termo a pesquisar: ");
                    String termo = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    List<Produto> resultado1 = pesquisar(produtos, termo, new CriterioDescricao());
                    if (resultado1.isEmpty()) {
                        System.out.println("Nenhum produto encontrado com essa descrição.");
                    } else {
                        for (Produto p : resultado1){
                            System.out.println(p);
                        } 
                    }
                    break;
                case "3":
                    System.out.print("Marca a pesquisar: ");
                    String marca = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    List<Produto> resultado2 = pesquisar(produtos, marca, new CriterioMarca());
                    if (resultado2.isEmpty()) {
                        System.out.println("Nenhum produto encontrado com essa marca.");
                    } else {
                        for (Produto p : resultado2){
                            System.out.println(p);
                        } 
                    }
                    break;
                case "4":
                    System.out.print("Preço máximo: ");
                    String precoMaximo = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    List<Produto> resultado3 = pesquisar(produtos, precoMaximo, new CriterioPrecoMaximo());
                    if (resultado3.isEmpty()) {
                        System.out.println("Nenhum produto encontrado com preço até " + precoMaximo);
                    } else {
                        for (Produto p : resultado3) {
                            System.out.println(p);
                        }
                    }
                    break;
                case "5":
                    System.out.print("Preço mínimo: ");
                    String precoMinimo = sc.nextLine();
                    System.out.println("\nResultado da pesquisa: ");
                    CriterioPrecoMinimo criterioPrecoMinimo = new CriterioPrecoMinimo();
                    List<Produto> resultado4 = pesquisar(produtos, precoMinimo, criterioPrecoMinimo);
                    if (resultado4.isEmpty()) {
                        System.out.println("Nenhum produto encontrado com preço a partir de " + precoMinimo);
                    } else {
                        for (Produto p : resultado4) {
                            System.out.println(p);
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        List<String> linhas;
        Path arquivo = Paths.get("produtos.txt");
        List<Produto> produtos = new ArrayList<>();

        try {
            linhas = Files.readAllLines(arquivo);
            for (String linha : linhas) {
                String[] c = linha.split(";");
                Produto p = new Produto(c[0], Double.parseDouble(c[1]), c[2]);
                produtos.add(p);
                
            }
            menuPrincipal(produtos);
        } catch (IOException e) {
            System.out.println("Erro: Arquivo 'produtos.txt' não encontrado ou não pode ser lido.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato de número inválido no arquivo 'produtos.txt'. Verifique se o preço está correto.");
        }
    }
}
