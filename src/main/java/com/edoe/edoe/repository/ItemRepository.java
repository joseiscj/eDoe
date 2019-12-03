package com.edoe.edoe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;

public interface ItemRepository extends MongoRepository<Item, Long>{
	Item findById(String id);
	List<Item> findAllByStatus(Status status);
	List<Item> findAllByDescriptionAndStatus(Description description, Status status);
	List<Item> findAllByStatusOrderByQuantityDesc(Status status);
	List<Item> findAllByStatusOrderByIdAsc(Status doacao);
}
