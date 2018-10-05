package com.mydomain.model;

import java.util.ArrayList;
import java.util.List;

public class Atm {
	
	//
	public static final int DEFAULT_BANKNOTE_QUANTITY = 100;
	private List<BanknoteLot> banknoteLots;
	
	public Atm(int bankNote1000Quantity,
			int bankNote500Quantity,
			int bankNote100Quantity,
			int bankNote50Quantity,
			int bankNote20Quantity) throws IllegalArgumentException{
		if(bankNote1000Quantity < 0 || 
				bankNote500Quantity < 0 || 
				bankNote100Quantity < 0 || 
				bankNote50Quantity < 0 || 
				bankNote20Quantity < 0) {
			throw new IllegalArgumentException("Banknote quantity must greater or equals 0");
		}
		banknoteLots = new ArrayList<>();	
		banknoteLots.add(new BanknoteLot(new Banknote(Banknote.TYPE_1000), bankNote1000Quantity));
		banknoteLots.add(new BanknoteLot(new Banknote(Banknote.TYPE_500), bankNote500Quantity));
		banknoteLots.add(new BanknoteLot(new Banknote(Banknote.TYPE_100), bankNote100Quantity));
		banknoteLots.add(new BanknoteLot(new Banknote(Banknote.TYPE_50), bankNote50Quantity));
		banknoteLots.add(new BanknoteLot(new Banknote(Banknote.TYPE_20), bankNote20Quantity));
	}
	
	public Atm() {
		this(DEFAULT_BANKNOTE_QUANTITY, DEFAULT_BANKNOTE_QUANTITY, DEFAULT_BANKNOTE_QUANTITY, 
				DEFAULT_BANKNOTE_QUANTITY, DEFAULT_BANKNOTE_QUANTITY);
	}
	
	public int getAtmTotalAmount() {
		int totalAmount = 0;
		for(BanknoteLot slot: banknoteLots) {
			totalAmount += (slot.getBanknote().getValue() * slot.getQuantity());
		}
		return totalAmount;
	}
	
	public List<BanknoteLot> getBanknoteLots() {
		return banknoteLots;
	}

	public List<BanknoteLot> withdraw(int amount) throws AtmWithdrawException{		
		List<BanknoteLot> paidBanknoteLots = new ArrayList<>();
		
		int atmTotalAmount = getAtmTotalAmount();
		if(amount > atmTotalAmount) {
			throw new AtmWithdrawException("Not Enough Money!: your withdraw[" 
					+ amount + "] is greater than avialable[" + atmTotalAmount + "]");
		}
		if(amount <= 0) {
			throw new AtmWithdrawException("Invalid withdraw amount!: your withdraw[" 
					+ amount + "] must greater than 0");
		}
		
		BanknoteLot lotBanknote1000 = banknoteLots.get(0);
		BanknoteLot lotBanknote500 = banknoteLots.get(1);
		BanknoteLot lotBanknote100 = banknoteLots.get(2);
		BanknoteLot lotBanknote50 = banknoteLots.get(3);
		BanknoteLot lotBanknote20 = banknoteLots.get(4);
		
		int tempAmount = amount;
		outer:
		for(int i0 = tempAmount / lotBanknote1000.getBanknoteValue(); i0 >= 0; i0--) {
			if(i0 > lotBanknote1000.getQuantity()) {
				continue;
			}
			tempAmount -= (lotBanknote1000.getBanknoteValue() * i0);
			if(tempAmount == 0) {
				paidBanknoteLots.add(new BanknoteLot(lotBanknote1000.getBanknote(), i0));
				paidBanknoteLots.add(new BanknoteLot(lotBanknote500.getBanknote(), 0));
				paidBanknoteLots.add(new BanknoteLot(lotBanknote100.getBanknote(), 0));
				paidBanknoteLots.add(new BanknoteLot(lotBanknote50.getBanknote(), 0));
				paidBanknoteLots.add(new BanknoteLot(lotBanknote20.getBanknote(), 0));
				break outer;
			}
			for(int i1 = tempAmount / lotBanknote500.getBanknoteValue(); i1 >= 0; i1--) {
				if(i1 > lotBanknote500.getQuantity()) {
					continue;
				}
				tempAmount -= (lotBanknote500.getBanknoteValue() * i1);
				if(tempAmount == 0) {
					paidBanknoteLots.add(new BanknoteLot(lotBanknote1000.getBanknote(), i0));
					paidBanknoteLots.add(new BanknoteLot(lotBanknote500.getBanknote(), i1));
					paidBanknoteLots.add(new BanknoteLot(lotBanknote100.getBanknote(), 0));
					paidBanknoteLots.add(new BanknoteLot(lotBanknote50.getBanknote(), 0));
					paidBanknoteLots.add(new BanknoteLot(lotBanknote20.getBanknote(), 0));
					break outer;
				}
				for(int i2 = tempAmount / lotBanknote100.getBanknoteValue(); i2 >= 0; i2--) {
					if(i2 > lotBanknote100.getQuantity()) {
						continue;
					}
					tempAmount -= (lotBanknote100.getBanknoteValue() * i2);
					if(tempAmount == 0) {
						paidBanknoteLots.add(new BanknoteLot(lotBanknote1000.getBanknote(), i0));
						paidBanknoteLots.add(new BanknoteLot(lotBanknote500.getBanknote(), i1));
						paidBanknoteLots.add(new BanknoteLot(lotBanknote100.getBanknote(), i2));
						paidBanknoteLots.add(new BanknoteLot(lotBanknote50.getBanknote(), 0));
						paidBanknoteLots.add(new BanknoteLot(lotBanknote20.getBanknote(), 0));
						break outer;
					}
					for(int i3 = tempAmount / lotBanknote50.getBanknoteValue(); i3 >= 0; i3--) {
						if(i3 > lotBanknote50.getQuantity()) {
							continue;
						}
						tempAmount -= (lotBanknote50.getBanknoteValue() * i3);
						if(tempAmount == 0) {
							paidBanknoteLots.add(new BanknoteLot(lotBanknote1000.getBanknote(), i0));
							paidBanknoteLots.add(new BanknoteLot(lotBanknote500.getBanknote(), i1));
							paidBanknoteLots.add(new BanknoteLot(lotBanknote100.getBanknote(), i2));
							paidBanknoteLots.add(new BanknoteLot(lotBanknote50.getBanknote(), i3));
							paidBanknoteLots.add(new BanknoteLot(lotBanknote20.getBanknote(), 0));
							break outer;
						}
						for(int i4 = tempAmount / lotBanknote20.getBanknoteValue(); i4 >= 0; i4--) {
							if(i4 > lotBanknote20.getQuantity()) {
								continue;
							}
							tempAmount -= (lotBanknote20.getBanknoteValue() * i4);
							if(tempAmount == 0) {
								paidBanknoteLots.add(new BanknoteLot(lotBanknote1000.getBanknote(), i0));
								paidBanknoteLots.add(new BanknoteLot(lotBanknote500.getBanknote(), i1));
								paidBanknoteLots.add(new BanknoteLot(lotBanknote100.getBanknote(), i2));
								paidBanknoteLots.add(new BanknoteLot(lotBanknote50.getBanknote(), i3));
								paidBanknoteLots.add(new BanknoteLot(lotBanknote20.getBanknote(), i4));
								break outer;
							}
							tempAmount += (lotBanknote20.getBanknoteValue() * i4);
						}
						tempAmount += (lotBanknote50.getBanknoteValue() * i3);
					}
					tempAmount += (lotBanknote100.getBanknoteValue() * i2);
				}
				tempAmount += (lotBanknote500.getBanknoteValue() * i1);
			}
			tempAmount += (lotBanknote1000.getBanknoteValue() * i0);
		}
		if(paidBanknoteLots.size() == 0) {
			throw new AtmWithdrawException("Can not pay exactly match amount!: " + amount);
		}

		for(int i = 0; i < banknoteLots.size(); i++) {
			banknoteLots.get(i).withdraw(paidBanknoteLots.get(i).getQuantity());
		}
		
		return paidBanknoteLots;
	}
}
