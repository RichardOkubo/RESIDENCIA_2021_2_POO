package org.serratec.residencia.poo.contas;

import org.serratec.residencia.poo.contas.tipos.TiposDeConta;

public abstract class Conta {

	private String cpf;
	private double saldo;
	private String nome;
	private String numeroDaConta;
	private String agencia;
	private TiposDeConta tipo;

	public Conta() {
	}

	public Conta(String cpf, String nome, double saldo, String numeroDaConta, String agencia, TiposDeConta tipo) {
		this.cpf = cpf;
		this.nome = nome;
		this.saldo = saldo;
		this.numeroDaConta = numeroDaConta;
		this.agencia = agencia;
		this.tipo = tipo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public String getAgencia() {
		return this.agencia;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public TiposDeConta getTipo() {
		return tipo;
	}

	public void setTipo(TiposDeConta tipo) {
		this.tipo = tipo;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean sacar(double valor) {
		if (valor <= this.getSaldo() && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			return true;
		}
		return false;
	}

	public boolean depositar(double valor) {
		if (valor > 0) {
			this.setSaldo(this.getSaldo() + valor);
			return true;
		}
		return false;
	}

	public boolean transferirA(double valor, Conta contaDoDestinatario) {
		if (valor <= this.getSaldo() && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			contaDoDestinatario.setSaldo(contaDoDestinatario.getSaldo() + valor);

			return true;
		}
		return false;
	}
	
	
	public boolean transferirB(double valor, Conta contaDoDestinatarioB) {
		if (valor <= this.getSaldo() && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			contaDoDestinatarioB.setSaldo(contaDoDestinatarioB.getSaldo() + valor);

			return true;
		}
		return false;
	}
	
}
