package com.edoe.edoe.models;

public class Doação {
	
	private Item donatedItem;
	private Item necessaryItem;
	
	private int quantity;

	public Doação(Item donatedItem, Item necessaryItem) {
		this.donatedItem = donatedItem;
		this.necessaryItem = necessaryItem;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getDonatedItem() {
		return donatedItem;
	}

	public void setDonatedItem(Item donatedItem) {
		this.donatedItem = donatedItem;
	}

	public Item getNecessaryItem() {
		return necessaryItem;
	}

	public void setNecessaryItem(Item necessaryItem) {
		this.necessaryItem = necessaryItem;
	}
	
	

}
