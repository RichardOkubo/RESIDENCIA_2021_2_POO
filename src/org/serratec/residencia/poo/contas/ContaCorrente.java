package org.serratec.residencia.poo.contas;

import org.serratec.residencia.poo.contas.tipos.TiposDeConta;
import org.serratec.residencia.poo.relatorios.Tributacao;

public class ContaCorrente extends Conta {
	//cheque especial
	
	public ContaCorrente() {
	}

	public ContaCorrente(String cpf, String nome, double saldo, String numeroDaConta, String agencia, TiposDeConta tipo) {
	super(cpf, nome, saldo, numeroDaConta, agencia, tipo);
	}
	
	@Override
	public boolean sacar(double valor) {

		if (valor <= this.getSaldo() && valor > 0) {
			this.setSaldo(this.getSaldo() - valor - 0.10); // tributaçao do saque (regra de negocio)
			
			Tributacao.valores.add(0.10);
			
			return true;
		}
		System.out.println("Saldo insuficiente ou valor incorreto para saque.");
		return false;
	}
	
	@Override
	public boolean depositar(double valor) {

		if (valor > 0) {
			this.setSaldo(this.getSaldo() + valor - 0.10); // tributa��o do dep�sito (regra de negocio)
			
			Tributacao.valores.add(0.10);
			
			return true;
		}
		System.out.print("Dados inv�lidos para dep�sito (Valor negativo)");
		return false;
	}
	
	@Override
	public boolean transferirA(double valor, Conta contaDoDestinatario) {
		if (valor <= this.getSaldo() && valor > 0) {
					
			this.setSaldo(this.getSaldo() - valor); // tributaçao da transferência (regra de negocio)
			
			Tributacao.valores.add(0.20);
			
			contaDoDestinatario.setSaldo(contaDoDestinatario.getSaldo() + valor);
			
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public boolean transferirB(double valor, Conta contaDoDestinatario) {
		if (valor <= this.getSaldo() && valor > 0) {
					
			this.setSaldo(this.getSaldo() - valor - 0.20); // tributaçao da transferência (regra de negocio)
			
			Tributacao.valores.add(0.20);
			
			contaDoDestinatario.setSaldo(contaDoDestinatario.getSaldo() + valor);
			
			return true;
		}
		
		return false;
	}
	
}