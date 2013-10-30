package br.com.devamil.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.devamil.model.Carta;
import br.com.devamil.model.Jogada;
import br.com.devamil.model.Mao;
import br.com.devamil.model.Naipe;
import br.com.devamil.model.Valor;


public class Jogadas {
	
	public static Mao getMelhorJogada(List<Carta> cartas) {
		Jogada jogada = null;
		List<Carta> mao = new ArrayList<Carta>();
		while (jogada == null) {
			if (possuiStraightFlush(cartas)) jogada = Jogada.STRAIGHT_FLUSH;
			else if (possuiFourOfKind(cartas)) jogada = Jogada.FOUR_OF_A_KIND;
			else if (possuiFullHouse(cartas)) jogada = Jogada.FULL_HOUSE;
			else if (possuiFlush(cartas)) jogada = Jogada.FLUSH;
			else if (possuiStraight(cartas)) jogada = Jogada.STRAIGHT;
			else if (possuiThreeOfKind(cartas)) jogada = Jogada.THREE_OF_A_KIND;
			else if (possuiTwoPair(cartas)) jogada = Jogada.TWO_PAIRS;
			else if (possuiPair(cartas)) jogada = Jogada.ONE_PAIR;
			else jogada = Jogada.HIGHEST_CARD;
		}
		mao.addAll(cartas);
		return new Mao(mao, jogada);
	}
	
	public static Carta getHighestCard(List<Carta> cartas) {
		Carta retorno = null;
		for (Carta carta : cartas) {
			if (retorno == null) retorno = carta;
			if (carta.getValor().getRank() < retorno.getValor().getRank()) {
				retorno = carta;
			}
		}
		return  retorno;
	}
	
	public static Carta getHighestCardComExcecao(List<Carta> cartas, Carta cartaExcecao) {
		Carta retorno = null;
		for (Carta carta : cartas) {
			if (carta.equals(cartaExcecao)) continue;
			if (retorno == null) retorno = carta;
			if (carta.getValor().getRank() < retorno.getValor().getRank()) {
				retorno = carta;
			}
		}
		return  retorno;
	}
	
	public static Carta getLessCard(List<Carta> cartas) {
		Carta retorno = null;
		for (Carta carta : cartas) {
			if (retorno == null) retorno = carta;
			if (carta.getValor().getRank() > retorno.getValor().getRank()) {
				retorno = carta;
			}
		}
		return  retorno;
	}
	public static boolean possuiPair(List<Carta> cartas) {
		boolean retorno = false;
		List<Carta> cartaAux = new ArrayList<Carta>();
		cartaAux.addAll(cartas);
		Carta carta = getHighestCard(cartaAux);
		while (cartaAux.size() > 0) {
			Carta cartaIgual = getPair(carta, cartaAux);
			if (cartaIgual != null) {
				retorno = true;
				break;
			} else {
				cartaAux.remove(carta);
			}
			if (cartaAux.size() > 0) carta = getHighestCard(cartaAux);
		}
		return retorno;
	}

	private static Carta getPair(Carta highCarta, List<Carta> cartas) {
		Carta retorno = null;
		for (Carta carta : cartas) {
			if (carta.equals(highCarta)) continue;
			if (highCarta.getValor().equals(carta.getValor())) {
				retorno = carta;
				break;
			}
		}
		return retorno;
	}
	
	public static boolean possuiTwoPair(List<Carta> mao) {
		boolean retorno = false;
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiPair(cartas)) {
			removePair(getPair(cartas), cartas);
			if (possuiPair(cartas)) {
				retorno = true;
			} 
		}
		return retorno;		
	}
	
	private static void removePair(List<Carta> cartasParaRemover, List<Carta> cartas) {
		cartas.removeAll(cartasParaRemover);
	}

	public static boolean possuiThreeOfKind(List<Carta> mao) {
		boolean retorno = false;
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiPair(cartas)) {
			List<Carta> pair = getPair(cartas);
			List<Carta> trinca = getThree(pair, cartas);
			if (trinca.isEmpty()) {
				cartas.removeAll(pair);
				if (possuiPair(cartas)) {
					pair = getPair(cartas);
					if (!pair.isEmpty()) {
						trinca = getThree(pair, cartas);
						if (!trinca.isEmpty()) {
							retorno = true;
						}
					}
				}
			} else {
				retorno = true;
			}
		} 
		return retorno;
	}
	
	private static List<Carta> getThreeOfKind(List<Carta> mao) {
		List<Carta> retorno = new ArrayList<Carta>();
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiPair(cartas)) {
			List<Carta> pair = getPair(cartas);
			List<Carta> trinca = getThree(pair, cartas);
			if (trinca.isEmpty()) {
				cartas.removeAll(pair);
				pair = getPair(cartas);
				if (!pair.isEmpty()) {
					trinca = getThree(pair, cartas);
					if (!trinca.isEmpty()) {
						retorno = trinca;
					}
				}
			} else {
				retorno = trinca;
			}
		} 
		return retorno;
	}

	private static List<Carta> getPair(List<Carta> cartas) {
		List<Carta> retorno = new ArrayList<Carta>();
		Carta carta = getHighestCard(cartas);
		Carta pair = null;
		while (pair == null) {
			pair = getPair(carta, cartas);
			if (pair == null) {
				cartas.remove(carta);
				carta = getHighestCard(cartas);
			} else {
				retorno.addAll(Arrays.asList(carta, pair));
			}
		}
		return retorno;
	}

	private static List<Carta> getThree(List<Carta> pair, List<Carta> cartas) {
		List<Carta> retorno = new ArrayList<Carta>();
		for (Carta carta : cartas) {
			if (pair.contains(carta)) continue;
			if (pair.get(0).getValor().equals(carta.getValor())) {
				retorno.addAll(pair);
				retorno.add(carta);
				break;
			}
		}
		return retorno;
	}
	
	public static boolean possuiStraight(List<Carta> cartas) {
		boolean retorno = false;
		if (!possuiPair(cartas) && cartas.size() == 5) {
			Carta primeiraCarta = getHighestCard(cartas);
			Carta segundaCarta = getHighestCardComExcecao(cartas, primeiraCarta);
			if (primeiraCarta.getValor().equals(Valor.AS)) {
				if (segundaCarta.getValor().getRank() == 10) {
					if (getLessCard(cartas).getValor().getRank() == Valor.DOIS.getRank()) {
						retorno = true;
					}
				} else {
					if (getStraightNormal(primeiraCarta, cartas)) retorno = true;
				}
			} else {
				if (getStraightNormal(primeiraCarta, cartas)) retorno = true;
			}
		}
		return retorno;
	}

	private static boolean getStraightNormal(Carta primeiraCarta, List<Carta> cartas) {
		boolean retorno = false;
		if (getLessCard(cartas).getValor().getRank() - 4 == primeiraCarta.getValor().getRank()) {
			retorno = true;
		}
		return retorno;
	}
	
	public static boolean possuiFlush(List<Carta> cartas) {
		boolean retorno = false;
		if (!possuiPair(cartas) && cartas.size() == 5) {
			retorno = true;
			Naipe naipe = getHighestCard(cartas).getNaipe();
			for (Carta carta : cartas) {
				if (!naipe.equals(carta.getNaipe())) {
					retorno = false;
					break;
				}
			}
		}
		return retorno;
	}
	
	public static boolean possuiFullHouse(List<Carta> mao) {
		boolean retorno = false;
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiThreeOfKind(cartas)) {
			cartas.removeAll(getThreeOfKind(mao));
			if (possuiPair(cartas)) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	public static boolean possuiFourOfKind(List<Carta> mao) {
		boolean retorno = false;
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiThreeOfKind(cartas)) {
			List<Carta> trinca = getThreeOfKind(mao);
			cartas.removeAll(trinca);
			if (getPair(trinca.get(0), cartas) != null) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	public static boolean possuiStraightFlush(List<Carta> mao) {
		boolean retorno = false;
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.addAll(mao);
		if (possuiStraight(cartas)) {
			if (possuiFlush(cartas)) {
				retorno = true;
			}
		}
		return retorno;
	}
}
