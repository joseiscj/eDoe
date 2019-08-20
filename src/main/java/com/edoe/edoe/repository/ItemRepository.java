package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	Item findById(long id);
}
