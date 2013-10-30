package br.com.devamil.model;

import java.util.Map;
import java.util.TreeMap;

public enum Jogada {

	STRAIGHT_FLUSH(1, "straight-flush (sequência numérica e de naipe)"), 
	FOUR_OF_A_KIND(2, "four-of-a-kind (quadra)"), 
	FULL_HOUSE(3, "full-house (trinca + par)"), 
	FLUSH(4, "flush (sequência de naipe)"), 
	STRAIGHT(5, "straight (sequência numérica)"), 
	THREE_OF_A_KIND(6, "three-of-a-kind (trinca)"), 
	TWO_PAIRS(7, "two-pairs (2 pares)"), 
	ONE_PAIR(8, "one-pair (1 par)"), 
	HIGHEST_CARD(9, "highest-card (maior carta) ");  
	
	private Integer rank;
	private String nome;
	
	private Jogada(Integer rank, String nome) {
		this.rank = rank;
		this.nome = nome;
	}
	
	@SuppressWarnings("serial")
	private static Map<Integer, Jogada> mapByRank= new TreeMap<Integer, Jogada>() {
		{
			for (Jogada jogada : Jogada.values()) {
				put(jogada.getRank(), jogada);
			}
		}
	};

	public Integer getRank() {
		return this.rank;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public static Jogada getByRank(Integer rank) {
		return Jogada.mapByRank.get(rank);
	}
}
