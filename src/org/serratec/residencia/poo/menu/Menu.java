package org.serratec.residencia.poo.menu;

import java.io.IOException;


import java.util.Scanner;

import org.serratec.residencia.poo.acesso.Acesso;
import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.contas.tipos.TiposDeConta;
import org.serratec.residencia.poo.database.BancoDeDados;
import org.serratec.residencia.poo.log.Log;
import org.serratec.residencia.poo.relatorios.Relatorio;
import org.serratec.residencia.poo.relatorios.Transacao;
import org.serratec.residencia.poo.relatorios.Tributacao;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.clientes.SeguroDeVida;
import org.serratec.residencia.poo.util.Tela;

public class Menu {

	public static void exibirMenuDoLogin(Scanner scan) throws IOException {
		String opcaoSelecionada;
		
		do {
			Tela.exibirA();
			opcaoSelecionada = scan.next();

			switch (opcaoSelecionada) {
			case "0":
				System.out.println("\nAté breve!");
				break;
			case "1":
				Usuario usuario = Acesso.logar(scan);

				if (usuario != null) {
					Transacao transacao = new Transacao();
					
					Menu.exibirMenuDoUsuario(scan, usuario, transacao);
				} else {
					System.out.println("\nDados inválidos! Tente novamente.");
				}
				break;
			default:
				System.out.println("\nOpção inválida! Tente novamente.");
				break;
			}
		} while (!opcaoSelecionada.equals("0"));
	}

	public static void exibirMenuDoUsuario(Scanner scan, Usuario usuario, Transacao transacao) throws IOException {
		
		String opcaoSelecionada;

		do {
			Tela.exibirB(usuario);

			opcaoSelecionada = scan.next();

			switch (opcaoSelecionada) {
			case "0":
				break;
			case "1":

				System.out.print("Digite o valor que deseja sacar: ");
				double valorDoSaque = scan.nextDouble();

				if (usuario.getConta().sacar(valorDoSaque)) {
					System.out.printf(
							"Saque realizado com sucesso!\nValor sacado: R$%.2f\nTaxa p/ saque: R$0,10\nSaldo atual: R$%.2f\n",
							valorDoSaque, usuario.getConta().getSaldo()
					);
					
					// Atualizando o saldo do usuário logado no banco de dados
					BancoDeDados.atualizar(usuario.getConta());
					
					transacao.setNumeroDeSaques();
					Log.comprovanteDeSaque(usuario.getConta(), valorDoSaque);
				} else {
					System.out.print("Saldo insuficiente ou valor incorreto para saque.");
				}

				break;
			case "2":
				System.out.print("Digite o valor que deseja depositar: ");
				double valorDoDeposito = scan.nextDouble();

				if (usuario.getConta().depositar(valorDoDeposito)) {
					System.out.printf(
							"Depósito realizado com sucesso!\nValor sacado: R$%.2f\nTaxa p/ saque: R$0,10\nSaldo atual: R$%.2f\n",
							valorDoDeposito, usuario.getConta().getSaldo()
					);
					
					// Atualizando o saldo do usuário logado no banco de dados
					BancoDeDados.atualizar(usuario.getConta());
					
					transacao.setNumeroDeDepositos();
					Log.comprovanteDeDeposito(usuario.getConta(), valorDoDeposito);
				} else {
					System.out.print("Dados inválidos (Valor negativo) ");
				}

				break;
			case "3":	
				String resposta;
				
				String cpfDoDestinatario;
				double valorDeTransferido;
				
				do {
					System.out.print("Selecione o tipo de transferência: \n" +
									 "0 - Sair\n1 - PIX\n" + "2 - TED\n");
					resposta = scan.next();
					
					switch (resposta) {
					case "0":
						break;
					case "1":
						System.out.print("Digite o CPF do destinatário: ");
						cpfDoDestinatario = scan.next();
						
						System.out.print("Digite o valor a ser tranferido: ");
						valorDeTransferido = scan.nextDouble();
						
						Conta contaDoDestinatarioA = Acesso.obterContaA(cpfDoDestinatario);
						
						if (contaDoDestinatarioA != null) {
							
							if (usuario.getConta().transferirA(valorDeTransferido, contaDoDestinatarioA)) {
								System.out.printf(
										"Operação realizada com sucesso!\nValor transferido: R$%.2f\nTaxa p/ transferência: R$0,20\nSaldo atual: R$%.2f\n",
										valorDeTransferido, usuario.getConta().getSaldo()
										);
								
								BancoDeDados.atualizar(usuario.getConta());
								
								BancoDeDados.atualizar(contaDoDestinatarioA);
								
								transacao.setNumeroDeTransferencias();
								Log.comprovanteTransferencia(usuario.getConta(), valorDeTransferido, contaDoDestinatarioA);
							} else {
								System.out.print("Saldo insuficiente ou valor incorreto para transferência ");
							}
						}
						break;
					case "2":
						System.out.print("Digite o CPF do destinatário: ");
						cpfDoDestinatario = scan.next();
						
						System.out.print("Digite a conta do destinatário: ");
						String contaDoDestinatario = scan.next();
						
						System.out.print("Digite a agência do destinatário: ");
						String agenciaDoDestinatario = scan.next();
					
						
						System.out.print("Digite o valor a ser tranferido: ");
						valorDeTransferido = scan.nextDouble();
						
						Conta contaDoDestinatarioB = Acesso.obterContaB(cpfDoDestinatario, contaDoDestinatario, agenciaDoDestinatario);
						
						if (contaDoDestinatarioB != null) {
							
							if (usuario.getConta().transferirB(valorDeTransferido, contaDoDestinatarioB)) {
								System.out.printf(
										"\nSua transferência foi realizada com sucesso!\nValor transferido: R$%.2f\nTaxa p/ tranferência: R$0,20\nSaldo atual: R$%.2f\n",
										valorDeTransferido, usuario.getConta().getSaldo()
										);
								transacao.setNumeroDeTransferencias();
								Log.comprovanteTransferencia(usuario.getConta(), valorDeTransferido, contaDoDestinatarioB);
							} else {
								System.out.print("Saldo insuficiente ou valor incorreto para transferência.");
							}
						}
						break;
					default:
						System.out.print("\nOpção inválida! ");
						break;
					}		
				} while (!resposta.equals("0") && !resposta.equals("1") && !resposta.equals("2"));				
				break;
			case "4":
				exibirMenuDeConsultas(scan, usuario, transacao);
				break;
			default:
				System.out.println("\nOpção inválida! Tente novamente.");
				break;
			}
		} while (!opcaoSelecionada.equals("0"));
	}

	public static void exibirMenuDeConsultas(Scanner scan, Usuario usuario, Transacao transacao) throws IOException {
		String opcaoSelecionada;

		do {
			Tela.exibirC(usuario);

			opcaoSelecionada = scan.next();

			switch (opcaoSelecionada) {
			case "0":
				break;
			case "1":
				System.out.printf("Saldo atual: R$%.2f", usuario.getConta().getSaldo());
				Log.comprovanteDoSaldo(usuario.getConta());
				break;
			case "2":
				switch (usuario.getTipoDeUsuario()) {
				case CLIENTE:
					String opcao;

					do {
						Tela.exibirE();

						opcao = scan.next();

						switch (opcao) {
						case "1":
							System.out.print("Informe o valor que será segurado: ");
							double valorDigitado = scan.nextDouble();

							usuario.setSeguroDeVida(new SeguroDeVida(valorDigitado));

							System.out.println("Taxa de tributação: R$" + usuario.getSeguroDeVida().getTributo());
							Tributacao.valores.add(usuario.getSeguroDeVida().getTributo());
							break;
						case "2":
							System.out.println("Que pena :(");
							break;
						}
					} while (!opcaoSelecionada.equals("2"));
					break;
				case GERENTE:
					usuario.setSeguroDeVida(new SeguroDeVida(1000));
					System.out.println("Taxa de tributação: R$" + usuario.getSeguroDeVida().getTributo());
					break;
				case DIRETOR:
					usuario.setSeguroDeVida(new SeguroDeVida(500));
					System.out.println("Taxa de tributação: R$" + usuario.getSeguroDeVida().getTributo());
					break;
				case PRESIDENTE:
					usuario.setSeguroDeVida(new SeguroDeVida(300));
					System.out.println("Taxa de tributação: R$" + usuario.getSeguroDeVida().getTributo());
					break;
				}
				break;
			case "3":
				if (usuario.getConta().getTipo().equals(TiposDeConta.CONTA_CORRENTE) &&
					!Tributacao.valores.isEmpty()) {
					Relatorio.exibirTributacaoCC(transacao);
					Log.relatorioDeTributacaoCC(usuario.getConta(), transacao);
				} else {
					System.out.println("Não foi realizada nenhuma operação ou o usuário não possui Conta Corrente (CC)");
				}
				break;
			case "4":
				Relatorio.exibirRendimentoCP(usuario, scan);
				break;
			case "5":
				switch (usuario.getTipoDeUsuario()) {
				case CLIENTE:
					System.out.println("\nOpção inválida! Tente novamente.");
					break;
				case GERENTE, DIRETOR, PRESIDENTE:
					Relatorio.exibirNumDeContas(usuario);
					break;
				default:
					break;
				}
				break;
			case "6":
				switch (usuario.getTipoDeUsuario()) {
				case CLIENTE, GERENTE:
					System.out.println("\nOpção inválida! Tente novamente.");
					break;
				case DIRETOR, PRESIDENTE:
					Relatorio.exibirInfoDosClientes(usuario);
					break;
				default:
					break;
				}
				break;
			case "7":
				switch (usuario.getTipoDeUsuario()) {
				case CLIENTE, GERENTE, DIRETOR:
					System.out.println("\nOpção inválida! Tente novamente.");
					break;
				case PRESIDENTE:
					Relatorio.exibirTotalDeCapitalArmazenado(usuario);
					break;
				default:
					break;
				}
				break;
			default:
				System.out.println("\nOpção inválida! Tente novamente.");
				break;
			}
		} while (!opcaoSelecionada.equals("0"));
	}
}