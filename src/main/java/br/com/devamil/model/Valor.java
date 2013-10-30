package br.com.devamil.model;

import java.util.Map;
import java.util.TreeMap;

public enum Valor {
	AS("A", 1), REI("K", 2), DAMA("Q", 3), VALETE("J", 4), DEZ("T", 5), NOVE("9", 6), OITO("8", 7), SETE("7", 8), SEIS("6", 9), CINCO("5", 10), QUATRO("4", 11), TRES("3", 12), DOIS("2", 13);

	private String sigla;
	private Integer rank;

	private Valor(String sigla, Integer rank) {
		this.sigla = sigla;
		this.rank = rank;
	}
	
	@SuppressWarnings("serial")
	private static Map<String, Valor> mapBySigla = new TreeMap<String, Valor>() {
		{
			for (Valor valor : Valor.values()) {
				put(valor.getSigla(), valor);
			}
		}
	};
	
	@SuppressWarnings("serial")
	private static Map<Integer, Valor> mapByRank= new TreeMap<Integer, Valor>() {
		{
			for (Valor valor : Valor.values()) {
				put(valor.getRank(), valor);
			}
		}
	};

	public String getSigla() {
		return this.sigla;
	}
	
	public Integer getRank() {
		return this.rank;
	}
	
	public static Valor getBySigla(String sigla) {
		return Valor.mapBySigla.get(sigla);
	}
	
	public static Valor getByRank(Integer rank) {
		return Valor.mapByRank.get(rank);
	}
}
