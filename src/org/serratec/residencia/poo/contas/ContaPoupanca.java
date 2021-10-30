package org.serratec.residencia.poo.contas;

import org.serratec.residencia.poo.contas.tipos.TiposDeConta;

public class ContaPoupanca extends Conta {
	
	public ContaPoupanca() {
	}
	
	public ContaPoupanca(String cpf, String nome, double saldo,String numeroDaConta, String agencia, TiposDeConta tipo) {
		super(cpf, nome, saldo, numeroDaConta, agencia, tipo);
	}
}