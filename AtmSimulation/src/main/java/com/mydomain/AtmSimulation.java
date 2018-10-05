package com.mydomain;

import java.util.List;
import java.util.Scanner;
import com.mydomain.model.Atm;
import com.mydomain.model.AtmWithdrawException;
import com.mydomain.model.BanknoteLot;

public class AtmSimulation {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Running..... ..\n\n");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please input initial banknote quantity." + "If not, default quantity is "
				+ Atm.DEFAULT_BANKNOTE_QUANTITY);
		System.out.println("------------------------------------------------------------------------\n");

		Atm atm;
		try {
			int banknote1000Quantity, banknote500Quantity, banknote100Quantity, banknote50Quantity, banknote20Quantity;

			System.out.print("Input number of 1000Baht banknote: ");
			banknote1000Quantity = Integer.parseInt(scanner.nextLine());

			System.out.print("Input number of 500Baht banknote: ");
			banknote500Quantity = Integer.parseInt(scanner.nextLine());

			System.out.print("Input number of 100Baht banknote: ");
			banknote100Quantity = Integer.parseInt(scanner.nextLine());

			System.out.print("Input number of 50Baht banknote: ");
			banknote50Quantity = Integer.parseInt(scanner.nextLine());

			System.out.print("Input number of 20Baht banknote: ");
			banknote20Quantity = Integer.parseInt(scanner.nextLine());

			atm = new Atm(banknote1000Quantity, banknote500Quantity, banknote100Quantity, banknote50Quantity,
					banknote20Quantity);
		} catch (NumberFormatException e) {			
			System.out.println("\nInvalid input! Banknote quantity must be integer");
			System.out.println("Atm will use default banknote quantity");
			atm = new Atm();
		} catch (IllegalArgumentException e) {
			System.out.println("\nInvalid input! Banknote quantity must greater or equals 0");
			System.out.println("Atm will use default banknote quantity");
			atm = new Atm();
		}
		System.out.println("\nINITIAL BANKNOTE: " + atm.getBanknoteLots() + " = " + atm.getAtmTotalAmount());
		System.out.println("\n");

		while (true) {
			try {
				System.out.print("Input withdraw amount: ");
				String input = scanner.nextLine();
				int withdraw = Integer.parseInt(input);

				System.out.println("BEFORE WITHDRAW:\t\t" + atm.getBanknoteLots() + " = " + atm.getAtmTotalAmount());

				List<BanknoteLot> paidBanknoteLots = atm.withdraw(withdraw);

				System.out.println("WITHDRAW:\t\t\t" + paidBanknoteLots + " = " + withdraw);
				System.out.println("REMAIN:\t\t\t\t" + atm.getBanknoteLots() + " = " + atm.getAtmTotalAmount());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			} catch (AtmWithdrawException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("\n\n");
		}

	}

}
