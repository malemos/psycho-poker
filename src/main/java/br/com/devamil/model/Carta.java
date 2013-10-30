package br.com.devamil.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"valor","naipe"})
public @Data class Carta implements Serializable {

	private static final long serialVersionUID = -13290602667356314L;
	
	private Valor valor;
	private Naipe naipe;
	
	public Carta(){}
	
	public Carta(Valor valor, Naipe naipe) {
		this.valor = valor;
		this.naipe = naipe;
	}
	public Carta(String carta) throws Exception {
		if (carta == null || carta.length() != 2) {
            throw new Exception("Carta não possui o formato adequado.");
        }
		if (!carta.matches("[0-9AKQJT][CDSH]")) {
			throw new Exception("Carta não possui o formato adequado.");
		}
		this.valor = Valor.getBySigla(carta.substring(0, 1));
		this.naipe = Naipe.getBySigla(carta.substring(1, 2));
	}

	@Override
	public String toString() {
		return this.valor.getSigla()+this.naipe.getSigla();
	}
}
