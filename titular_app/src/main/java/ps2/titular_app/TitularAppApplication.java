package ps2.titular_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static ps2.titular_app.ES.*;

@SpringBootApplication
public class TitularAppApplication implements CommandLineRunner {

	@Autowired
	private TitularDao dao;

	public static void main(String[] args) {
		SpringApplication.run(TitularAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("# GERENCIADOR DE TITULARES!");
		boolean sair = false;
		String menu = "\n(1) Listar todos os titulares";
		menu += "\n(2) Buscar um titular específico pelo número";
		menu += "\n(3) Criar um novo titular";
		menu += "\n(4) Alterar os dados do titular";
		menu += "\n(5) Apagar um titular";
		menu += "\n(0) Sair\n";
		menu += "Escolha uma opção: ";

		while (!sair) {
			String op = input(menu);
			switch (op) {
				case "1":
					dao.lerTudo();
					break;
				case "2":
					dao.buscarPorId();
					break;
				case "3":
					dao.criar();
					break;
				case "4":
					dao.alterar();
					break;
				case "5":
					dao.apagar();
					break;
				case "0":
					sair = true;
					break;
				default:
					print("Opção inválida!");
			}
		}
	}
}