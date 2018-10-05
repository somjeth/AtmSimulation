package com.mydomain.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BanknoteTest {

	@Test
	public void testConstructor() {
		Banknote banknote1000 = new Banknote(Banknote.TYPE_1000);
		assertEquals(Banknote.VALUE_1000,	banknote1000.getValue());
		
		Banknote banknote500 = new Banknote(Banknote.TYPE_500);
		assertEquals(Banknote.VALUE_500,	banknote500.getValue());
		
		Banknote banknote100 = new Banknote(Banknote.TYPE_100);
		assertEquals(Banknote.VALUE_100,	banknote100.getValue());
		
		Banknote banknote50 = new Banknote(Banknote.TYPE_50);
		assertEquals(Banknote.VALUE_50,	banknote50.getValue());
		
		Banknote banknote20 = new Banknote(Banknote.TYPE_20);
		assertEquals(Banknote.VALUE_20,	banknote20.getValue());
	}

}
