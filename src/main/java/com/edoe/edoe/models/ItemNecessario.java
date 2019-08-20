package com.edoe.edoe.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class ItemNecessario extends Item{
	
	private static final long serialVersionUID = 1L;

}
