package org.serratec.residencia.poo.usuarios;

import org.serratec.residencia.poo.contas.Conta;

import org.serratec.residencia.poo.usuarios.clientes.SeguroDeVida;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;

public abstract class Usuario {

	private String cpf;
	private String senha;
	private String nome;
	private TipoDeUsuario tipoDeUsuario;
	private Conta conta;
	private SeguroDeVida seguroDeVida;

	public Usuario() {
	}

	public Usuario(String cpf, String senha, String nome, TipoDeUsuario tipoDeUsuario, Conta conta) {
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
		this.tipoDeUsuario = tipoDeUsuario;
		this.conta = conta;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public SeguroDeVida getSeguroDeVida() {
		return seguroDeVida;
	}

	public void setSeguroDeVida(SeguroDeVida seguroDeVida) {
		this.seguroDeVida = seguroDeVida;
	}
	

}
