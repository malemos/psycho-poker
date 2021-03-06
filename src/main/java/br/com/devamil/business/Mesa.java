package br.com.devamil.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import br.com.devamil.model.Carta;
import br.com.devamil.model.Jogada;
import br.com.devamil.model.Mao;

public @Data class Mesa {

	private Baralho baralho = new Baralho();
	private List<Carta> mao = new ArrayList<Carta>();
	private List<Carta> monte = new ArrayList<Carta>();
	
	private final Integer QUANTIDADE_CARTAS = 5;

	private void carregarCartas(String[] cartasAux) throws Exception {
		iniciarJogo();
		if (cartasAux == null || cartasAux.length != 10) {
			throw new Exception("Entrada inválida");
		}
		for (int i = 0; i < cartasAux.length; i++) {
			if (i < QUANTIDADE_CARTAS) {
				mao.add(new Carta(cartasAux[i]));
			} else {
				monte.add(new Carta(cartasAux[i]));
			}
		}
		this.baralho.removerCartas(mao);
		this.baralho.removerCartas(monte);
	}

	private void iniciarJogo() {
		this.baralho.iniciarMao();
		this.mao.clear();
		this.monte.clear();
	}
	
	public void melhorJogada(String cartasEntrada) throws Exception {
		this.melhorJogada(cartasEntrada.split(" "));
	}
	
	public void melhorJogada(String[] cartasEntrada) throws Exception {
		if (cartasEntrada == null || cartasEntrada.length != 10) {
			throw new Exception("Entrada inválida");
		}
		this.carregarCartas(cartasEntrada);
		Carta[] cartas = new Carta[QUANTIDADE_CARTAS];
		Mao mao = Jogadas.getMelhorJogada(this.mao);
		if (!mao.getJogada().equals(Jogada.STRAIGHT_FLUSH)) {
			for (int i = 0; i < QUANTIDADE_CARTAS; i++) {
				cartas[i] = this.monte.get(i);
				mao = trocarCartas(cartas, mao, i+1);
				if (mao.getJogada().equals(Jogada.STRAIGHT_FLUSH)) break;
			}
		}
		this.monte.addAll(this.mao);
		this.monte.removeAll(mao.getMao());
		this.setMao(mao.getMao());
		System.out.println(" Mao: " + this.getMao() + " Monte: " + this.getMonte() + " A melhor jogada foi: " + mao.getJogada().getNome());
	}

	private Mao trocarCartas(Carta[] cartas, Mao mao, Integer quantidadeCartasMonte) {
		Mao melhorMao = mao;
		if (quantidadeCartasMonte == 1) {
			for (int i = 0; i < QUANTIDADE_CARTAS; i++) {
					for (int j = quantidadeCartasMonte; j < QUANTIDADE_CARTAS; j++) {
						Carta carta = null;
						if (j+i < QUANTIDADE_CARTAS) {
							carta = this.mao.get(j+i);
						} else {
							carta = this.mao.get(j+i-QUANTIDADE_CARTAS);
						}
						cartas[j] = carta;
					}
					melhorMao = validarMao(cartas, melhorMao);
			}
		} else if (quantidadeCartasMonte < 5) {
			melhorMao = trocaCarta(cartas, quantidadeCartasMonte, 0, melhorMao);
		} else {
			melhorMao = validarMao(cartas, melhorMao);
		}
		return melhorMao;
	}

	private Mao validarMao(Carta[] cartas, Mao melhorMao) {
		Mao jogada = Jogadas.getMelhorJogada(Arrays.asList(cartas));
		if (jogada.getJogada().getRank() < melhorMao.getJogada().getRank()) {
			melhorMao = jogada;
		}
		return melhorMao;
	}

	private Mao trocaCarta(Carta[] cartas, Integer quantidadeCartasMonte, Integer inicio, Mao melhorMao) {
		for (int i = inicio; i <= quantidadeCartasMonte; i++) {
			cartas[quantidadeCartasMonte] = this.mao.get(i);
			if (quantidadeCartasMonte < QUANTIDADE_CARTAS-1)
				melhorMao = trocaCarta(cartas, quantidadeCartasMonte+1, i+1, melhorMao);
			else
				melhorMao = validarMao(cartas, melhorMao);
		}
		return melhorMao;
	}
}
