package org.serratec.residencia.poo.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.database.BancoDeDados;
import org.serratec.residencia.poo.relatorios.Transacao;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.funcionarios.Gerente;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;

public class Log {
	
	static final String PATH_BASICO = "./logs/";
	static final String EXTENSAO = ".txt";
	
	public static void comprovanteDeSaque(Conta conta, double valorDoSaque) throws IOException {
		
		String arquivo = conta.getCpf() + "_" +
						 conta.getAgencia() + "_" +
						 conta.getNumeroDaConta() + "_transacoes";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** SAQUE ***************";
			
			buffWrite.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "Valor: R$" + valorDoSaque;
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM DO SAQUE ***************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void comprovanteDeDeposito(Conta conta, double valorDoDeposito) throws IOException {
		
		String arquivo = conta.getCpf() + "_" +
						 conta.getAgencia() + "_" +
						 conta.getNumeroDaConta() + "_transacoes";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** DEPÓSITO ***************";
			buffWrite.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "Valor: R$" + valorDoDeposito;
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM DO DEPÓSITO ***************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void comprovanteTransferencia(Conta conta, double valorDeTransferencia, Conta destino)
			throws IOException {
		
		String linha;
		
		SimpleDateFormat simpleDateFormat;
		Date date;
		
		String arquivoDoRemetente = conta.getCpf() + "_" +
								 	conta.getAgencia() + "_" +
								 	conta.getNumeroDaConta() + "_transacoes";
						
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivoDoRemetente + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			linha = "*************** TRANSFERÊNCIA REALIZADA ***************";
			buffWrite.append(linha + "\n\n");

			linha = "*********** DADOS DO REMETENTE **********";
			buffWrite.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "AgÃªncia: " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "********** DADOS DO DESTINATÁRIO ***********";
			buffWrite.append(linha + "\n");

			linha = "CPF: " + destino.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + destino.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + destino.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "***********************************************";
			buffWrite.append(linha + "\n");

			linha = "Valor: R$" + valorDeTransferencia;
			buffWrite.append(linha + "\n");

			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM DA TRANSFERÊNCIA ***************";
			buffWrite.append(linha + "\n\n");
		}

		String arquivoDoDestinatario = destino.getCpf() + "_" +
									   destino.getAgencia() + "_" +
									   destino.getNumeroDaConta() + "_transacoes";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivoDoDestinatario + EXTENSAO, true);
		     BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			linha = "*************** TRANSFERÊNCIA RECEBIDA ***************";
			buffWrite.append(linha + "\n\n");

			linha = "*********** DADOS DO REMETENTE **********";
			buffWrite.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "********** DADOS DO DESTINATÁRIO ***********";
			buffWrite.append(linha + "\n");

			linha = "CPF: " + destino.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + destino.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + destino.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "***********************************************";
			buffWrite.append(linha + "\n");

			linha = "Valor: R$" + valorDeTransferencia;
			buffWrite.append(linha + "\n");

			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM DA TRANSFERÊNCIA ***************";
			buffWrite.append(linha + "\n");
		}
	}
	
	public static void relatorioDeTributacaoCC(Conta conta, Transacao transacao) throws IOException {
		
		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "tributacao";

		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** TOTAL DE TRIBUTAÇÕES ***************";
			buffWrite.append(linha + "\n\n");

			linha = "Total gasto com transações = R$" + transacao.getGastoComTransacao();
			buffWrite.append(linha + "\n");

			linha = "Taxa para saque = R$0,10";
			buffWrite.append(linha + "\n");

			linha = "Total de saques realizados = " + transacao.getNumeroDeSaques();
			buffWrite.append(linha + "\n\n");

			linha = "Taxa para deposito = R$0,10";
			buffWrite.append(linha + "\n");

			linha = "Total de depósitos realizados = " + transacao.getNumeroDeDepositos();
			buffWrite.append(linha + "\n\n");

			linha = "Taxa para tranferência = R$0,20";
			buffWrite.append(linha + "\n");

			linha = "Total de tranferências realizadas = " + transacao.getNumeroDeTransferencias();
			buffWrite.append(linha + "\n\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "****************************************************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void relatorioDeRendimentoCP(Conta conta, double valor, int dias) throws IOException {

		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "_rendimento";

		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
				 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** SIMULAÇÃO DE RENDIMENTO ***************";
			buffWrite.append(linha + "\n\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "*******************************************************";
			buffWrite.append(linha + "\n\n");

			linha = "Valor simulado = R$" + valor;
			buffWrite.append(linha + "\n");

			linha = "Dias de rendimento = " + dias;
			buffWrite.append(linha + "\n");

			linha = "Total estimado = R$" + (valor + ((valor * 0.01) * dias));
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*******************************************************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void relatorioContasPorAgencia(Usuario usuario) throws IOException {

		Conta conta = usuario.getConta();
		
		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "_contas_por_agencia";
		
		int totalContas = 0;
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {

			String linha = "********************** INFORMAÇÕES DO RESPONSÁVEL **********************";
			buffWrite.append(linha + "\n\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência : " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "*******************************************************";
			buffWrite.append(linha + "\n\n");

			linha = "*************** TOTAL DE CONTAS NA MESMA AGÊNCIA ***************";
			buffWrite.append(linha + "\n\n");
			
			List<Conta> listaDeContas = usuario.getTipoDeUsuario().equals(TipoDeUsuario.GERENTE) ?
					 BancoDeDados.selecionarContas((Gerente) usuario) : BancoDeDados.selecionarTodasAsContas();
			
			for (Conta c : listaDeContas) {
				linha = "CPF: " + c.getCpf();
				buffWrite.append(linha + "\n");

				linha = "Agência : " + c.getAgencia();
				buffWrite.append(linha + "\n");

				linha = "Conta: " + c.getNumeroDaConta();
				buffWrite.append(linha + "\n");
				
				linha = "Tipo: " + c.getTipo();
				buffWrite.append(linha + "\n");

				totalContas++;

				linha = "**********************************";
				buffWrite.append(linha + "\n");
			}

			linha = "Total de contas: " + totalContas;
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "************************************************************************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void relatorioTotalClientes(Conta conta) throws IOException {

		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "_total_clientes";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "************************* TOTAL DE CLIENTES *************************";
			buffWrite.append(linha + "\n\n");
			
			List<Conta> listaDeContas = BancoDeDados.selecionarTodasAsContas();
			
			Collections.sort(listaDeContas, Comparator.comparing(Conta::getNome)); // ProgramaÃ§Ã£o Funcional
			
			for (Conta c : listaDeContas) {
				linha = c.getCpf();
				buffWrite.append(linha + "\n");
				
				linha = c.getAgencia();
				buffWrite.append(linha + "\n");
				
				linha = c.getNome();
				buffWrite.append(linha + "\n");

				linha = "****************************************************************";
				buffWrite.append(linha + "\n");
			}

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*********************************************************************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void relatorioComTotalDeCapitalArmazenado(Conta conta, double saldoTotal) throws IOException {
		
		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "_total_capital";

		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "************************* TOTAL DE CAPITAL ARMAZENADO *************************";
			buffWrite.append(linha + "\n\n");

			linha = "Capital total armazenado no banco: R$" + saldoTotal;
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*******************************************************************************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
	public static void comprovanteDoSaldo(Conta conta) throws IOException {

		String arquivo = conta.getCpf() + "_" +
				 		 conta.getAgencia() + "_" +
				 		 conta.getNumeroDaConta() + "_comprovanteSaldo";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** SALDO ***************";
			buffWrite.append(linha + "\n");

			linha = "Tipo: " + conta.getTipo();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "Saldo: R$" + conta.getSaldo();
			buffWrite.append(linha + "\n");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM ***************";
			buffWrite.append(linha + "\n");
		}
		
	}
	
public static void comprovanteSeguroDeVida(Conta conta, double valorDoSeguro) throws IOException {
		
		String arquivo = conta.getCpf() + "_" +
						 conta.getAgencia() + "_" +
						 conta.getNumeroDaConta() + "_transacoes";
		
		try (FileWriter fileWriter = new FileWriter(PATH_BASICO + arquivo + EXTENSAO, true);
			 BufferedWriter buffWrite = new BufferedWriter(fileWriter)) {
			
			String linha = "*************** SEGURO DE VIDA ***************";
			
			buffWrite.append(linha + "\n");

			linha = "CPF: " + conta.getCpf();
			buffWrite.append(linha + "\n");

			linha = "Agência: " + conta.getAgencia();
			buffWrite.append(linha + "\n");

			linha = "Conta: " + conta.getNumeroDaConta();
			buffWrite.append(linha + "\n");

			linha = "Valor: R$" + valorDoSeguro;
			buffWrite.append(linha + "\n");

			linha = "Débito da contratação do Seguro: R$" + (valorDoSeguro * 0.2);
			buffWrite.append(linha + "\n");
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			
			linha = "Operação realizada em: " + simpleDateFormat.format(date);
			buffWrite.append(linha + "\n");

			linha = "*************** FIM DO SAQUE ***************";
			buffWrite.append(linha + "\n\n");
		}
	}
	
}
