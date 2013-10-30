package br.com.devamil.model;

import java.util.Map;
import java.util.TreeMap;

public enum Naipe {
	PAUS("C"), OURO("D"), ESPADA("S"), COPAS("H");

	private String sigla;

	private Naipe(String sigla) {
		this.sigla = sigla;
	}

	@SuppressWarnings("serial")
	private static Map<String, Naipe> mapBySigla = new TreeMap<String, Naipe>() {
		{
			for (Naipe naipe : Naipe.values()) {
				put(naipe.getSigla(), naipe);
			}
		}
	};

	public String getSigla() {
		return sigla;
	}

	public static Naipe getBySigla(String sigla) {
		return Naipe.mapBySigla.get(sigla);
	}
}
