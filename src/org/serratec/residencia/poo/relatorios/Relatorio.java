package org.serratec.residencia.poo.relatorios;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.contas.tipos.TiposDeConta;
import org.serratec.residencia.poo.database.BancoDeDados;
import org.serratec.residencia.poo.log.Log;
import org.serratec.residencia.poo.usuarios.funcionarios.Gerente;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;
import org.serratec.residencia.poo.util.Tela;

public class Relatorio {
	
	public static void exibirTributacaoCC(Transacao transacao) {
		double soma = 0;
		
		for (double valor : Tributacao.valores) soma += valor;

		Tela.exibirD(soma, transacao);
	}
	
	public static void exibirRendimentoCP(Usuario usuario, Scanner scan) throws IOException {
		if (usuario.getConta().getTipo().equals(TiposDeConta.CONTA_POUPANCA)) {
			
			System.out.print("Informe o valor que deseja simular: ");
			double valorSimulado = scan.nextDouble();
			
			System.out.print("Informe o número de dias que deseja simular: ");
			int diasSimulados = scan.nextInt();
			
			double rendimento = ((valorSimulado * 0.01) * diasSimulados);

			System.out.printf("Rendimento: R$%.2f\nValor final: R$%.2f\n",
					rendimento, (valorSimulado + rendimento));
			
			Log.relatorioDeRendimentoCP(usuario.getConta(), valorSimulado, diasSimulados);
		} else {
			System.out.println("Conta Corrente não possui rendimento.");
		}
	}

	public static void exibirNumDeContas(Usuario usuario) throws IOException {
		
		List<Conta> listaDeContas = usuario.getTipoDeUsuario().equals(TipoDeUsuario.GERENTE) ?
				 BancoDeDados.selecionarContas((Gerente) usuario) : BancoDeDados.selecionarTodasAsContas();
		
		for (Conta conta : listaDeContas) {
			System.out.printf("CPF: %s\tNº: %s\tAGÊNCIA: %s\t%s\t R$%.2f\n",
					conta.getCpf(), conta.getNumeroDaConta(), conta.getAgencia(), conta.getTipo(), conta.getSaldo());
		}
		
		Log.relatorioContasPorAgencia(usuario);
	}
	
	public static void exibirInfoDosClientes(Usuario usuario) throws IOException {
		List<Conta> listaDeContas = BancoDeDados.selecionarTodasAsContas();
		
		Collections.sort(listaDeContas, Comparator.comparing(Conta::getNome)); // Programação Funcional
		
		for (Conta conta : listaDeContas)
			System.out.printf("CPF: %s\tAGÊNCIA: %s\t NOME: %s\n",
					conta.getCpf(), conta.getAgencia(), conta.getNome());
		
		Log.relatorioTotalClientes(usuario.getConta());
	}
	
	public static void exibirTotalDeCapitalArmazenado(Usuario usuario) throws IOException {
		List<Conta> listaDeContas = BancoDeDados.selecionarTodasAsContas();
		
		double saldoTotal = 0;
		
		for (Conta conta : listaDeContas) saldoTotal += conta.getSaldo();
		
		System.out.printf("Capital total armazenado no banco: R$%.2f\n", saldoTotal);
		
		Log.relatorioComTotalDeCapitalArmazenado(usuario.getConta(), saldoTotal);
	}
}
