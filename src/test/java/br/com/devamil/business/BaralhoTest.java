package br.com.devamil.business;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.devamil.business.Baralho;
import br.com.devamil.model.Carta;

public class BaralhoTest {
	
	@Test
	public void iniciarJogoTest() {
		Baralho baralho = new Baralho();
		baralho.iniciarMao();
		Assert.assertTrue(baralho.getCartas().size() == 52);
	}

	@Test
	public void getCartasJogadorTest() {
		Integer quantidadeCartas = 5;
		Baralho baralho = new Baralho();
		baralho.iniciarMao();
		List<Carta> cartasJogador = baralho.getCartasBaralho(quantidadeCartas);
		Assert.assertTrue(cartasJogador.size() == quantidadeCartas);
	}
}
