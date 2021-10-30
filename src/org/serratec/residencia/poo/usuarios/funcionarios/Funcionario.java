package org.serratec.residencia.poo.usuarios.funcionarios;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;

public abstract class Funcionario extends Usuario {
	
	//private String cargo;

	public Funcionario() {
	}

	public Funcionario(String cpf, String senha, String nome, TipoDeUsuario tipoDeUsuario, Conta conta) {
		super(cpf, senha, nome, tipoDeUsuario, conta);
	}
	


}