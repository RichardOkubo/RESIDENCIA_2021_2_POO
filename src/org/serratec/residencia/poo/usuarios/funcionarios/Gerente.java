package org.serratec.residencia.poo.usuarios.funcionarios;


import org.serratec.residencia.poo.contas.Conta;
//import org.serratec.residencia.poo.usuarios.Usuario;

import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;


public class Gerente extends Funcionario {
	
	private String agenciaGerida;

	public Gerente() {
	}
	
	public Gerente(String cpf, String senha, String nome, TipoDeUsuario tipoDeUsuario, Conta conta, String agenciaGerida) {
		super(cpf, senha, nome, tipoDeUsuario, conta);
		this.agenciaGerida = agenciaGerida;
	}

	public String getAgenciaGerida() {
		return agenciaGerida;
	}
	
	public void setAgenciaGerida(String agencia) {
		this.agenciaGerida = agencia;
	}
	
}