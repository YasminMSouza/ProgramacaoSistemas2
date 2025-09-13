import java.math.BigDecimal;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";

        Connection connection = ConnectionFactory.getConnection(url);
                if (connection == null) {
                    System.out.println("Falha ao conectar ao banco de dados. Saindo.");
                    return;
                }

                ContaDao dao = new ContaDao(connection);
                Scanner scanner = new Scanner(System.in);
                int opcao = -1;

                do {
                    System.out.println("(1) Listar todas as contas");
                    System.out.println("(2) Buscar uma conta pelo número");
                    System.out.println("(3) Criar uma nova conta");
                    System.out.println("(4) Alterar o saldo de uma conta");
                    System.out.println("(5) Apagar uma conta");
                    System.out.println("(0) Sair");
                    System.out.print("Escolha uma opção: ");

                    try {
                        opcao = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (opcao) {
                            case 1:
                                listarTodasContas(dao);
                                break;
                            case 2:
                                buscarContaPeloNumero(dao, scanner);
                                break;
                            case 3:
                                criarNovaConta(dao, scanner);
                                break;
                            case 4:
                                atualizarSaldoConta(dao, scanner);
                                break;
                            case 5:
                                apagarConta(dao, scanner);
                                break;
                            case 0:
                                System.out.println("Acabou, tchauzinho :)");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.nextLine(); 
                        opcao = -1; 
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro: " + e.getMessage());
                    }

                } while (opcao != 0);

                scanner.close();
                connection.close();
            }
            
            private static void listarTodasContas(ContaDao dao) throws Exception {
                System.out.println("\n--- Listando Todas as Contas ---");
                List<Conta> contas = dao.lerTodas();
                if (contas.isEmpty()) {
                    System.out.println("Nenhuma conta encontrada.");
                } else {
                    contas.forEach(System.out::println);
                }
            }

            private static void buscarContaPeloNumero(ContaDao dao, Scanner scanner) throws Exception {
                System.out.print("Digite o número da conta para buscar: ");
                long numeroConta = scanner.nextLong();
                Conta conta = dao.buscarPeloNumero(numeroConta);
                if (conta != null) {
                    System.out.println("Conta encontrada: " + conta);
                } else {
                    System.out.println("Conta com o número " + numeroConta + " não encontrada.");
                }
            }

            private static void criarNovaConta(ContaDao dao, Scanner scanner) throws Exception {
                System.out.print("Digite o número da nova conta: ");
                long numero = scanner.nextLong();
                System.out.print("Digite o saldo inicial: ");
                BigDecimal saldo = scanner.nextBigDecimal();
                Conta novaConta = new Conta(numero, saldo);
                if (dao.criar(novaConta)) {
                    System.out.println("Conta criada com sucesso!");
                } else {
                    System.out.println("Falha ao criar conta. Ela pode já existir.");
                }
            }
            
            private static void atualizarSaldoConta(ContaDao dao, Scanner scanner) throws Exception {
                System.out.print("Digite o número da conta para atualizar: ");
                long numero = scanner.nextLong();
                Conta conta = dao.buscarPeloNumero(numero);
                if (conta != null) {
                    System.out.print("Digite o novo saldo: ");
                    BigDecimal novoSaldo = scanner.nextBigDecimal();
                    conta.setSaldo(novoSaldo);
                    if (dao.atualizar(conta)) {
                        System.out.println("Saldo da conta atualizado com sucesso!");
                    } else {
                        System.out.println("Falha ao atualizar o saldo da conta.");
                    }
                } else {
                    System.out.println("Conta com o número " + numero + " não encontrada.");
                }
            }
            
            private static void apagarConta(ContaDao dao, Scanner scanner) throws Exception {
                System.out.print("Digite o número da conta para apagar: ");
                long numero = scanner.nextLong();
                Conta conta = new Conta(numero, BigDecimal.ZERO); 
                if (dao.apagar(conta)) {
                    System.out.println("Conta apagada com sucesso!");
                } else {
                    System.out.println("Falha ao apagar a conta. Ela pode não existir.");
                }
            }
        }
   // }
//}
