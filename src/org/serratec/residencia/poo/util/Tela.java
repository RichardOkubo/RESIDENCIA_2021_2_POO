package org.serratec.residencia.poo.util;


import org.serratec.residencia.poo.relatorios.Transacao;
import org.serratec.residencia.poo.usuarios.Usuario;

public class Tela {

	public static void exibirA() {
		StringBuilder sbLogin = new StringBuilder("\n")
				.append("+--------------------------------------------------------+").append("\n")
				.append("|                  Bem-vindo ao SysBank!                 |").append("\n")
				.append("+--------------------------------------------------------+").append("\n")
				.append("|                Escolha uma das operações:              |").append("\n")
				.append("|                                                        |").append("\n")
				.append("|  0  -  Sair                                            |").append("\n")
				.append("|  1  -  Login                                           |").append("\n")
				.append("+--------------------------------------------------------+").append("\n").append("> ");
		
		System.out.print(sbLogin.toString());
	}
	
	public static void exibirAA() {
		StringBuilder sbCpf = new StringBuilder("\n")
				.append("+--------------------------------------------------------+").append("\n")
				.append("| Digite seu CPF                                         |").append("\n")
				.append("+--------------------------------------------------------+").append("\n").append("> ");
		limpar();
		System.out.print(sbCpf.toString());
	}
	
	public static void exibirAB() {
		StringBuilder sbSenha = new StringBuilder("\n")
				.append("+--------------------------------------------------------+").append("\n")
				.append("| Digite sua senha                                       |").append("\n")
				.append("+--------------------------------------------------------+").append("\n").append("> ");
		limpar();
		System.out.print(sbSenha.toString());
	}

	public static void exibirB(Usuario usuario) {
		StringBuilder sbUsuario = new StringBuilder("\n")
				.append("+--------------------------------------------------------+").append("\n").append("| Nome:    ")
				.append(usuario.getNome()).append("\n").append("| Usuario: ").append(usuario.getTipoDeUsuario())
				.append("\n").append("| Tipo:    ").append(usuario.getConta().getTipo()).append("\n")
				.append("+--------------------------------------------------------+").append("\n")
				.append("|                Escolha uma das operações:              |").append("\n")
				.append("|                                                        |").append("\n")
				.append("|  0  -  Sair                                            |").append("\n")
				.append("|  1  -  Sacar                                           |").append("\n")
				.append("|  2  -  Depositar                                       |").append("\n")
				.append("|  3  -  Transferir                                      |").append("\n")
				.append("|  4  -  Consutar                                        |").append("\n")
				.append("+--------------------------------------------------------+").append("\n").append("> ");
		System.out.print(sbUsuario.toString());
	}

	public static void exibirC(Usuario usuario) {

		int n = 0;

		StringBuilder sbConsulta = new StringBuilder("\n")
				.append("+------------------------------------------------------------------+").append("\n")
				.append("| Nome:    ").append(usuario.getNome()).append("\n").append("| Usuario: ")
				.append(usuario.getTipoDeUsuario()).append("\n").append("| Tipo:    ")
				.append(usuario.getConta().getTipo()).append("\n")
				.append("+------------------------------------------------------------------+").append("\n")
				.append("|                Escolha uma das operações:                        |").append("\n")
				.append("|                                                                  |").append("\n")
				.append("|   ").append(n++).append("  -  Sair                                                     |")
				.append("\n").append("|   ").append(n++)
				.append("  -  Saldo                                                    |").append("\n").append("|   ")
				.append(n++).append("  -  Seguro de vida                                           |").append("\n")
				.append("|   ").append(n++).append("  -  Relatório tributação da CC                               |")
				.append("\n").append("|   ").append(n++)
				.append("  -  Relatório rendimento da CP                               |").append("\n");

		switch (usuario.getTipoDeUsuario()) {
		case CLIENTE:
			break;
		case GERENTE:
			sbConsulta.append("|   ").append(n++)
					.append("  -  Relatório Nº de contas nesta agência                     |").append("\n");
			break;
		case DIRETOR:
			sbConsulta.append("|   ").append(n++)
					.append("  -  Relatório Nº total de contas nas agências                |").append("\n")
					.append("|   ").append(n++)
					.append("  -  Relatório c/ informações dos clientes                    |").append("\n");
			break;
		case PRESIDENTE:
			sbConsulta.append("|   ").append(n++)
					.append("  -  Relatório Nº total de contas nas agências                |").append("\n")
					.append("|   ").append(n++)
					.append("  -  Relatório c/ informações dos clientes                    |").append("\n")
					.append("|   ").append(n++)
					.append("  -  Relatório c/ valor total armazenado no banco             |").append("\n");
			break;
		default:
			break;
		}

		sbConsulta.append("+------------------------------------------------------------------+").append("\n")
				.append("> ");

		System.out.print(sbConsulta.toString());
	}

	public static void exibirD(double soma, Transacao transacao) {
		StringBuilder sbTributacao = new StringBuilder("\n")
				.append("Para cada saque será cobrado o valor de: R$ 0.10").append("\n")
				.append(transacao.getNumeroDeSaques()).append("\n")
				.append("Para cada depósito será cobrado o valor de: R$ 0.10").append("\n")
				.append(transacao.getNumeroDeDepositos()).append("\n")
				.append("Para cada transferência será cobrado o valor de: R$ 0.20").append("\n")
				.append(transacao.getNumeroDeTransferencias()).append("\n")
				.append("Caso opte por ter o seguro de vida, o tributo será de 20% sobre o valor segurado.").append("\n")
				.append("Total gasto nas operações: R$");

		System.out.printf("%s%.2f", sbTributacao.toString(), soma);
	}

	public static void exibirE() {
		StringBuilder sbSeguro = new StringBuilder().append("Deseja contratar o seguro de vida? ").append("\n")
				.append("OBS: Será cobrada uma taxa de 20% sobre o valor segurado como tributo.").append("\n")
				.append("[1] Sim").append("\t").append("[2] NÃ£o").append("\n").append("> ");

		System.out.println(sbSeguro.toString());
	}

	public static void limpar() {
		for (int i = 0; i < 50; i++)
			System.out.println();
	}
}
