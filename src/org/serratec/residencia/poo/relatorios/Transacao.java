package org.serratec.residencia.poo.relatorios;

public class Transacao {
	
	private int numeroDeDepositos;
	private int numeroDeTransferencias;
	private int numeroDeSaques;
	private double gastoComTransacao;

	public int getNumeroDeDepositos() {
		return numeroDeDepositos;
	}
	
	public void setNumeroDeDepositos() {
		this.numeroDeDepositos++;
		setGastoComTransacao(0.10);
	}
	
	public int getNumeroDeSaques() {
		return numeroDeSaques;
	}
	
	public void setNumeroDeSaques() {
		this.numeroDeSaques++;
		setGastoComTransacao(0.10);
	}
	
	public int getNumeroDeTransferencias() {
		return numeroDeTransferencias;
	}
	
	public void setNumeroDeTransferencias() {
		this.numeroDeTransferencias++;
		setGastoComTransacao(0.20);
	}
	
	
	public double getGastoComTransacao() {
		return gastoComTransacao;
	}
	
	public void setGastoComTransacao(double valor) {
		this.gastoComTransacao += valor;
	}
}
