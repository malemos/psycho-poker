package br.com.devamil.model;

import java.util.List;

import lombok.Data;

public @Data class Mao {

	private List<Carta> mao;
	
	private Jogada jogada;

	public Mao(List<Carta> mao, Jogada jogada) {
		this.mao = mao;
		this.jogada = jogada;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		for (Carta carta : this.mao) {
			if (retorno.length() > 0) {
				retorno.append(" ");
			}
			retorno.append(carta.toString());
		}
		return retorno.toString();
	}
}
