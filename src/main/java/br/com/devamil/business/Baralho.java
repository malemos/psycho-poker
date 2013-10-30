package br.com.devamil.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.devamil.model.Carta;
import br.com.devamil.model.Naipe;
import br.com.devamil.model.Valor;
import lombok.Data;

public @Data class Baralho {

	private Set<Carta> cartas = new HashSet<Carta>();
	
	public void iniciarMao() {
		cartas = new HashSet<Carta>();
		for (Valor valor : Valor.values()) {
			for (Naipe naipe : Naipe.values()) {
				cartas.add(new Carta(valor, naipe));
			}
		}
	}

	public List<Carta> getCartasBaralho(Integer quantidadeCartas) {
		if (this.cartas.isEmpty()) {
			this.iniciarMao();
		}
		List<Carta> cartas = new ArrayList<Carta>();
		for (Carta carta : this.cartas) {
			cartas.add(carta);
			if (cartas.size() == quantidadeCartas) {
				break;
			}
		}
		
		this.cartas.removeAll(cartas);
		return cartas;
	}

	public void removerCartas(List<Carta> cartas) {
		this.cartas.removeAll(cartas);
	}
}
