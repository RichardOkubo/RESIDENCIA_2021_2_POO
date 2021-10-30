package org.serratec.residencia.poo.main;

import java.io.IOException;

import java.util.Scanner;

import org.serratec.residencia.poo.menu.Menu;

// TODO acessar pct util/classe tela para fazer revisão textual do menu
//Classe Relatorio tbm

public class SistemaInterno {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		Menu.exibirMenuDoLogin(scan);
		
		scan.close();
	}
}