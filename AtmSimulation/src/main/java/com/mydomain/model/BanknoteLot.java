package com.mydomain.model;

public class BanknoteLot {
	private Banknote banknote;
	private int quantity;
	
	public BanknoteLot(Banknote banknote, int quantity) {
		this.banknote = banknote;
		this.quantity = quantity;
	}

	public Banknote getBanknote() {
		return banknote;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int getBanknoteValue() {
		return banknote.getValue();
	}
	
	public int getTotalAmount() {
		return quantity * getBanknoteValue();
	}

	@Override
	public String toString() {
		return banknote + "(qty=" + quantity + ")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banknote == null) ? 0 : banknote.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BanknoteLot other = (BanknoteLot) obj;
		if (banknote == null) {
			if (other.banknote != null)
				return false;
		} else if (!banknote.equals(other.banknote))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	public boolean isEmpty() {
		return quantity == 0;
	}
	
	public void withdraw(int withdrawQuantity) {
		quantity -= withdrawQuantity;
	}
	
}
