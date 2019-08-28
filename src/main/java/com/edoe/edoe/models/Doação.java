package com.edoe.edoe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doação implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
