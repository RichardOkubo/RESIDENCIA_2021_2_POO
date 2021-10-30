package org.serratec.residencia.poo.usuarios.clientes;

import org.serratec.residencia.poo.contas.Conta;
import org.serratec.residencia.poo.usuarios.Usuario;
import org.serratec.residencia.poo.usuarios.tipos.TipoDeUsuario;

public class Cliente extends Usuario {

	public Cliente() {
	}

	public Cliente(String cpf, String senha, String nome, TipoDeUsuario tipoDeUsuario, Conta conta) {
		super(cpf, senha, nome, tipoDeUsuario, conta);
	}
	
}