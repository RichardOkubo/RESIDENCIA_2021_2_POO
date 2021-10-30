package org.serratec.residencia.poo.usuarios.clientes;

public class SeguroDeVida {

	private double tributo;
	
	public SeguroDeVida(double valorDigitado) {
		this.tributo = valorDigitado * 0.2;
	}
	
	public double getTributo() {
		return tributo;
	}

	public void setTributo(double tributo) {
		this.tributo = tributo;
	}
	
}
