package br.com.devamil.business;

import org.junit.Test;

public class MesaTest {

	@Test
	public void melhorJogadaTest() throws Exception {
		Mesa mesa = new Mesa();
		mesa.melhorJogada("TH JH QC QD QS QH KH AH 2S 6S");
		mesa.melhorJogada("2H 2S 3H 3S 3C 2D 3D 6C 9C TH");
		mesa.melhorJogada("2H 2S 3H 3S 3C 2D 9C 3D 6C TH");
		mesa.melhorJogada("2H AD 5H AC 7H AH 6H 9H 4H 3C");
		mesa.melhorJogada("AC 2D 9C 3S KD 5S 4D KS AS 4C");
		mesa.melhorJogada("KS AH 2H 3C 4H KC 2C TC 2D AS");
		mesa.melhorJogada("AH 2C 9S AD 3C QH KS JS JD KD");
		mesa.melhorJogada("6C 9C 8C 2D 7C 2H TC 4C 9S AH");
		mesa.melhorJogada("3D 5S 2H QD TD 6S KH 9H AD QH ");
	}
}
