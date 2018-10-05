package com.mydomain.model;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import com.mydomain.model.Atm;
import com.mydomain.model.AtmWithdrawException;
import com.mydomain.model.Banknote;
import com.mydomain.model.BanknoteLot;

public class AtmTest {

	@Test
	public void testWithdraw() throws AtmWithdrawException {
		Atm atm = new Atm();
		int numberOfBanknote1000 = atm.getBanknoteLots().get(0).getQuantity();
		int numberOfBanknote500 = atm.getBanknoteLots().get(1).getQuantity();
		int numberOfBanknote100 = atm.getBanknoteLots().get(2).getQuantity();
		int numberOfBanknote50 = atm.getBanknoteLots().get(3).getQuantity();
		int numberOfBanknote20 = atm.getBanknoteLots().get(4).getQuantity();

		int amountBeforeWithdraw = atm.getAtmTotalAmount();
		int withdraw = 1610;

		System.out.println("BEFORE WITHDRAW:\t" + atm.getBanknoteLots() + " = " + amountBeforeWithdraw);

		List<BanknoteLot> paidBanknoteLots = atm.withdraw(withdraw);

		int amountAfterWithdraw = atm.getAtmTotalAmount();
		int paidAmount = 0;
		for (BanknoteLot banknoteLot : paidBanknoteLots) {
			paidAmount += banknoteLot.getTotalAmount();
		}

		System.out.println("WITHDRAW:\t\t" + paidBanknoteLots + " = " + withdraw);
		System.out.println("REMAIN:\t\t\t" + atm.getBanknoteLots() + " = " + atm.getAtmTotalAmount());

		assertEquals(withdraw, paidAmount);
		assertEquals(amountBeforeWithdraw - withdraw, amountAfterWithdraw);

		// compare banknote 1000
		assertEquals(numberOfBanknote1000 - paidBanknoteLots.get(0).getQuantity(),
				atm.getBanknoteLots().get(0).getQuantity());

		// compare banknote 500
		assertEquals(numberOfBanknote500 - paidBanknoteLots.get(1).getQuantity(),
				atm.getBanknoteLots().get(1).getQuantity());

		// compare banknote 100
		assertEquals(numberOfBanknote100 - paidBanknoteLots.get(2).getQuantity(),
				atm.getBanknoteLots().get(2).getQuantity());

		// compare banknote 50
		assertEquals(numberOfBanknote50 - paidBanknoteLots.get(3).getQuantity(),
				atm.getBanknoteLots().get(3).getQuantity());

		// compare banknote 20
		assertEquals(numberOfBanknote20 - paidBanknoteLots.get(4).getQuantity(),
				atm.getBanknoteLots().get(4).getQuantity());
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithNotEnoughMoney() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(20000);
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithInvalidWithdraw1() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(0);
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithInvalidWithdraw2() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(-100);
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithNotExactlyMatchAmount1() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(800);
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithNotExactlyMatchAmount2() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(30);
	}

	@Test(expected = AtmWithdrawException.class)
	public void testWithdrawWithNotExactlyMatchAmount3() throws AtmWithdrawException {
		Atm atm1 = new Atm(1, 1, 1, 1, 1);
		atm1.withdraw(140);
	}

	@Test
	public void testGetAtmTotalAmount() {
		Atm atm1 = new Atm();
		int defaultTotalAmount = Banknote.VALUE_1000 * Atm.DEFAULT_BANKNOTE_QUANTITY
				+ Banknote.VALUE_500 * Atm.DEFAULT_BANKNOTE_QUANTITY
				+ Banknote.VALUE_100 * Atm.DEFAULT_BANKNOTE_QUANTITY + Banknote.VALUE_50 * Atm.DEFAULT_BANKNOTE_QUANTITY
				+ Banknote.VALUE_20 * Atm.DEFAULT_BANKNOTE_QUANTITY;
		assertEquals(167000, atm1.getAtmTotalAmount());
		assertEquals(167000, defaultTotalAmount);
		assertEquals(defaultTotalAmount, atm1.getAtmTotalAmount());

		atm1 = new Atm(0, 0, 0, 0, 0);
		assertEquals(0, atm1.getAtmTotalAmount());

		atm1 = new Atm(0, 0, 1, 0, 2);
		assertEquals(140, atm1.getAtmTotalAmount());
	}

	@Test()
	public void testEmptyConstructorWithDefaultArgument() {
		Atm atm1 = new Atm(Atm.DEFAULT_BANKNOTE_QUANTITY, Atm.DEFAULT_BANKNOTE_QUANTITY, Atm.DEFAULT_BANKNOTE_QUANTITY,
				Atm.DEFAULT_BANKNOTE_QUANTITY, Atm.DEFAULT_BANKNOTE_QUANTITY);
		Atm atm2 = new Atm();
		assertEquals(atm1.getBanknoteLots(), atm2.getBanknoteLots());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalArgument1() {
		@SuppressWarnings("unused")
		Atm atm1 = new Atm(1, 1, 1, 1, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalArgument2() {
		@SuppressWarnings("unused")
		Atm atm1 = new Atm(1, 1, 1, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalArgument3() {
		@SuppressWarnings("unused")
		Atm atm1 = new Atm(1, 1, -1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalArgument4() {
		@SuppressWarnings("unused")
		Atm atm1 = new Atm(1, -1, 1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithIllegalArgument5() {
		@SuppressWarnings("unused")
		Atm atm1 = new Atm(-1, 1, 1, 1, 1);
	}

}
