package org.serratec.residencia.poo.acesso;

import java.io.IOException;

import java.util.Scanner;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.contas.ContaCorrente;
import org.serratec.residencia.poo.contas.ContaPoupanca;
import org.serratec.residencia.poo.contas.tipos.TiposDeConta;
import org.serratec.residencia.poo.database.BancoDeDados;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.clientes.Cliente;
import org.serratec.residencia.poo.usuarios.funcionarios.Diretor;
import org.serratec.residencia.poo.usuarios.funcionarios.Gerente;
import org.serratec.residencia.poo.usuarios.funcionarios.Presidente;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;
import org.serratec.residencia.poo.util.Tela;

public class Acesso {

	public static Usuario logar(Scanner scan) throws IOException {
		Tela.exibirAA();
		String cpf = scan.next();

		Tela.exibirAB();
		String senha = scan.next();

		String[] registro = BancoDeDados.obterCpf(cpf);

		if (autenticar(senha, registro)) {
			return obterUsuario(scan, registro);
		}
		return null;
	}

	public static boolean autenticar(String senha, String[] registro) {
		if (registro == null) return false;

		if (senha.equals(registro[1])) return true;

		return false;
	}

	public static Usuario obterUsuario(Scanner scan, String[] registro) {
		Usuario usuario = null;

		String cpf = registro[0]; 							// 000.000.000-00;
		String senha = registro[1]; 						// 0;
		String nome = registro[2]; 							// Eduardo;
		String cc = registro[3]; 							// true; CC
		double saldoCC = Double.parseDouble(registro[4]); 	// 10000;
		String cp = registro[5];							// false; CP	
		double saldoCP = Double.parseDouble(registro[6]); 	// 0;
		String numeroDaConta = registro[7]; 				// 12345;
		String agencia = registro[8]; 						// 001;
		String tipoDeUsuario = registro[9]; 				// PRESIDENTE

		Conta conta = gerarConta(cpf, nome, agencia, numeroDaConta, cc, saldoCC, cp, saldoCP, scan);

		switch (tipoDeUsuario) {
		case "CLIENTE":
			usuario = new Cliente(cpf, senha, nome, TipoDeUsuario.CLIENTE, conta);
			break;
		case "GERENTE":
			usuario = new Gerente(cpf, senha, nome, TipoDeUsuario.GERENTE, conta, agencia);
			break;
		case "DIRETOR":
			usuario = new Diretor(cpf, senha, nome, TipoDeUsuario.DIRETOR, conta, agencia);
			break;
		case "PRESIDENTE":
			usuario = new Presidente(cpf, senha, nome, TipoDeUsuario.PRESIDENTE, conta, agencia);
			break;
		}

		return usuario;
	}

	public static Conta obterContaA(String cpfDoDestinatario) throws IOException {
		Conta conta = null;

		String[] registro = BancoDeDados.obterCpf(cpfDoDestinatario);

		if (registro != null) {
			if (registro[3].equals("t")) {
				conta = gerarConta(registro[0], registro[2], registro[4], registro[7], registro[8]);
			} else {
				System.out.println("Destinatário não possui Conta Corrente (CC)");
			}
		} else {
			System.out.println("O destinatário não possui conta cadastrada");
		}

		return conta;
	}
	
	
	public static Conta obterContaB(String cpfDoDestinatario, String contaDoDestinatario, String agenciaDoDestinatario) throws IOException {
		Conta conta = null;

		String[] registro = BancoDeDados.obterContaAgencia(cpfDoDestinatario, contaDoDestinatario, agenciaDoDestinatario);

		if (registro != null) {
			if (registro[3].equals("t")) {
				conta = gerarConta(registro[0], registro[2], registro[4], registro[7], registro[8]);
			} else {
				System.out.println("O destinatário não possui uma Conta Corrente (CC)");
			}
		} else {
			System.out.println("O destinatário não possui conta cadastrada");
		}

		return conta;
	}
	

	public static Conta gerarConta(String cpf, String nome, String saldo, String numeroDaConta, String agencia) {
		double saldoDaConta = Double.parseDouble(saldo);

		return new ContaCorrente(cpf, nome, saldoDaConta, numeroDaConta, agencia, TiposDeConta.CONTA_CORRENTE);
	}

	public static Conta gerarConta(String cpf, String nome, String agencia, String numeroDaConta, String cc, double saldoCC, String cp, double saldoCP, Scanner scan) {
		Conta conta = null;
		String resposta;

		if (cc.equals("t") && cp.equals("t")) {		
			do {
				System.out.print("Qual tipo de conta você deseja logar?\n 1 - CC\n 2 - CP\n> ");
				resposta = scan.next();
				
				switch (resposta) {
				case "1":
					conta = new ContaCorrente(cpf, nome, saldoCC, numeroDaConta, agencia, TiposDeConta.CONTA_CORRENTE);
					break;
				case "2":
					conta = new ContaPoupanca(cpf, nome, saldoCP, numeroDaConta, agencia, TiposDeConta.CONTA_POUPANCA);
					break;
				default:
					System.out.print("\nOpção inválida!");
					break;
				}		
			} while (!resposta.equals("1") && !resposta.equals("2"));
		} else if (cc.equals("t")) {
			conta = new ContaCorrente(cpf, nome, saldoCC, numeroDaConta, agencia, TiposDeConta.CONTA_CORRENTE);
		} else {
			conta = new ContaPoupanca(cpf, nome, saldoCP, numeroDaConta, agencia, TiposDeConta.CONTA_POUPANCA);
		}
		return conta;
	}
}
