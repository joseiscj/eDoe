package com.edoe.edoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;

public interface ItemRepository extends JpaRepository<Item, Long>{
	Item findById(long id);
	List<Item> findByStatus(Status status);
	List<Item> findByDescriptionAndStatus(Description description, Status status);
	List<Item> findByStatusOrderByQuantityDesc(Status status);
	List<Item> findByStatusOrderByIdAsc(Status doacao);
}
