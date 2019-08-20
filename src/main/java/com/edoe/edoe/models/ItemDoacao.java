package com.edoe.edoe.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class ItemDoacao extends Item{
	
	private static final long serialVersionUID = 1L;

}
