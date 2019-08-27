package com.edoe.edoe.util;
import java.util.Comparator;

import com.edoe.edoe.models.Item;

public class SortByScore implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		return o2.getMatchScore() - o1.getMatchScore();
	}

}
