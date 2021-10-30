package org.serratec.residencia.poo.database;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.contas.ContaCorrente;
import org.serratec.residencia.poo.contas.ContaPoupanca;
import org.serratec.residencia.poo.contas.tipos.TiposDeConta;
import org.serratec.residencia.poo.usuarios.funcionarios.Gerente;

public class BancoDeDados {

	final static String PATH = "./database/";
	final static String FILE = "database.txt";
	
	public static void atualizar(Conta conta) throws IOException {
		
		Path path = Paths.get(PATH + FILE);
		
		int posicaoDaLinha = 0;
		
	    List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
	    
	    for (String linha : linhas) {
	    	
	    	if (linha.startsWith(conta.getCpf())) {
	    		String[] registro = linha.split(";");
	    		
	    		if (conta.getTipo().equals(TiposDeConta.CONTA_CORRENTE)) {
	    			registro[4] = Double.toString(conta.getSaldo());
	    		} else {
	    			registro[6] = Double.toString(conta.getSaldo());
	    		}
		
	    		String registro_alterado = String.join(";", registro);
	    		
	    		linhas.set(posicaoDaLinha, registro_alterado);
	    	}
	    	
	    	posicaoDaLinha++;
	    }
	    
	    Files.write(path, linhas, StandardCharsets.UTF_8);
	}

	public static String[] obterCpf(String cpf) throws IOException {
		String[] registro = null;
		String linha;

		try (FileReader fileReader = new FileReader(PATH + FILE);
				BufferedReader buffRead = new BufferedReader(fileReader)) {

			while ((linha = buffRead.readLine()) != null) {

				String[] colunas = linha.trim().split(";");

				if (colunas[0].equals(cpf)) {
					registro = colunas;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado no local especificado!" + PATH + FILE);
		}
		
		return registro;
	}
	
	public static String[] obterContaAgencia(String cpf, String conta, String agencia) throws IOException {
		String[] registro = null;
		String linha;

		try (FileReader fileReader = new FileReader(PATH + FILE);
				BufferedReader buffRead = new BufferedReader(fileReader)) {

			while ((linha = buffRead.readLine()) != null) {

				String[] colunas = linha.trim().split(";");

				if (colunas[0].equals(cpf) && colunas[7].equals(conta) && colunas[8].equals(agencia) ) {
					registro = colunas;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado no local especificado!" + PATH + FILE);
		}
		
		return registro;
	}

	public static List<Conta> selecionarContas(Gerente gerente) throws IOException {
		List<Conta> listaDeContas = new ArrayList<>();

		String linhaDoArquivo;

		try (FileReader fileReader = new FileReader(PATH + FILE);
				BufferedReader buffRead = new BufferedReader(fileReader)) {

			while ((linhaDoArquivo = buffRead.readLine()) != null) {

				String[] colunas = linhaDoArquivo.split(";");

				if (colunas[8].equals(gerente.getAgenciaGerida())) {

					if (colunas[3].equals("t") && colunas[5].equals("t")) {

						addListaDeContasCC(listaDeContas, colunas);
						addListaDeContasCP(listaDeContas, colunas);

					} else if (colunas[3].equals("t")) {
						addListaDeContasCC(listaDeContas, colunas);
					} else {
						addListaDeContasCP(listaDeContas, colunas);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado no local especificado!" + PATH + FILE);
		}

		return listaDeContas;
	}

	public static List<Conta> selecionarTodasAsContas() throws IOException {
		List<Conta> listaDeContas = new ArrayList<>();

		String linhaDoArquivo;

		try (FileReader fileReader = new FileReader(PATH + FILE);
			 BufferedReader buffRead = new BufferedReader(fileReader)) {

			while ((linhaDoArquivo = buffRead.readLine()) != null) {

				String[] colunas = linhaDoArquivo.split(";");

				if (colunas[3].equals("t") && colunas[5].equals("t")) {
					
					addListaDeContasCC(listaDeContas, colunas);
					addListaDeContasCP(listaDeContas, colunas);

				} else if (colunas[3].equals("t")) {
					addListaDeContasCC(listaDeContas, colunas);
				} else {
					addListaDeContasCP(listaDeContas, colunas);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado no local especificado!" + PATH + FILE);
		}
		return listaDeContas;
	}	
	
	public static void addListaDeContasCC(List<Conta> listaDeContas, String[] colunas) {
		listaDeContas.add(new ContaCorrente(
				colunas[0],
				colunas[2],
				Double.parseDouble(colunas[4]),
				colunas[7],
				colunas[8],
				TiposDeConta.CONTA_CORRENTE));
	}
	
	public static void addListaDeContasCP(List<Conta> listaDeContas, String[] colunas) {
		listaDeContas.add(new ContaPoupanca(
				colunas[0],
				colunas[2],
				Double.parseDouble(colunas[6]),
				colunas[7],
				colunas[8],
				TiposDeConta.CONTA_POUPANCA));
	}
}
