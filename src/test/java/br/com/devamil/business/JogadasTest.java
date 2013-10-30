package br.com.devamil.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.devamil.model.Carta;

public class JogadasTest {

	@Test
	public void possuiHighestCardTest() throws Exception {
		List<Carta> cartasJogador = Arrays.asList(new Carta("AD"), new Carta("QD"), new Carta("JD"), new Carta("TD"), new Carta("9D"));
		Assert.assertEquals(new Carta("AD"), Jogadas.getHighestCard(cartasJogador));
	}
	
	@Test
	public void possuiPairTest() throws Exception {
		List<Carta> cartasJogador = Arrays.asList(new Carta("6C"), new Carta("9C"), new Carta("9S"), new Carta("TC"), new Carta("2H"));
		Assert.assertTrue(Jogadas.possuiPair(cartasJogador));
	}
	
	@Test
	public void possuiTwoPairTest() throws Exception {
		List<Carta> cartasJogador = Arrays.asList(new Carta("6C"), new Carta("9C"), new Carta("9S"), new Carta("TC"), new Carta("6H"));
		Assert.assertTrue(Jogadas.possuiTwoPair(cartasJogador));
	}
	
	@Test
	public void possuiThreeOfKindTest() throws Exception {
		List<Carta> cartasJogador = Arrays.asList(new Carta("6C"), new Carta("9C"), new Carta("9S"), new Carta("TC"), new Carta("9H"));
		Assert.assertTrue(Jogadas.possuiThreeOfKind(cartasJogador));
	}
	
	@Test
	public void possuiStraightAsCincoTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("2C"));
		cartasJogador.add(new Carta("3D"));
		cartasJogador.add(new Carta("4H"));
		cartasJogador.add(new Carta("5H"));
		Assert.assertTrue(Jogadas.possuiStraight(cartasJogador));
	}
	
	@Test
	public void possuiStraightAsDezTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("KC"));
		cartasJogador.add(new Carta("QD"));
		cartasJogador.add(new Carta("JH"));
		cartasJogador.add(new Carta("TH"));
		Assert.assertTrue(Jogadas.possuiStraight(cartasJogador));
	}
	
	@Test
	public void possuiStraightFailedTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("KC"));
		cartasJogador.add(new Carta("QD"));
		cartasJogador.add(new Carta("JH"));
		cartasJogador.add(new Carta("9H"));
		Assert.assertFalse(Jogadas.possuiStraight(cartasJogador));
	}
	
	@Test
	public void possuiFlushTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("KH"));
		cartasJogador.add(new Carta("QH"));
		cartasJogador.add(new Carta("JH"));
		cartasJogador.add(new Carta("9H"));
		Assert.assertTrue(Jogadas.possuiFlush(cartasJogador));
	}
	
	@Test
	public void possuiFullHouseTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("AC"));
		cartasJogador.add(new Carta("AD"));
		cartasJogador.add(new Carta("TH"));
		cartasJogador.add(new Carta("TD"));
		Assert.assertTrue(Jogadas.possuiFullHouse(cartasJogador));
	}
	
	@Test
	public void possuiFourOfKindTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("AC"));
		cartasJogador.add(new Carta("AD"));
		cartasJogador.add(new Carta("AS"));
		cartasJogador.add(new Carta("TD"));
		Assert.assertTrue(Jogadas.possuiFourOfKind(cartasJogador));
	}
	
	@Test
	public void possuiStraightFlushTest() throws Exception {
		List<Carta> cartasJogador = new ArrayList<Carta>();
		cartasJogador.add(new Carta("AH"));
		cartasJogador.add(new Carta("KH"));
		cartasJogador.add(new Carta("QH"));
		cartasJogador.add(new Carta("JH"));
		cartasJogador.add(new Carta("TH"));
		Assert.assertTrue(Jogadas.possuiStraightFlush(cartasJogador));
	}
}
