package br.com.devamil.app;

import java.util.Arrays;

import br.com.devamil.business.Mesa;

public class Application {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length == 0) {
			System.out.println("Favor informar uma entrada de cartas para verificar melhor jogada");
		} else {
			Mesa mesa = new Mesa();
			for (int i = 0; i < args.length; i=i+10) {
				String[] strings = Arrays.copyOfRange(args, i, i+10);
				mesa.melhorJogada(strings);
			}
		}
	}
}
