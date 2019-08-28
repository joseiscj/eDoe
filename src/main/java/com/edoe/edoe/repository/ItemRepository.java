package com.edoe.edoe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	Item findById(long id);
	List<Item> findAllByStatus(Status status);
	List<Item> findAllByDescriptionAndStatus(Description description, Status status);
	List<Item> findAllByStatusOrderByQuantityDesc(Status status);
	List<Item> findAllByStatusOrderByIdAsc(Status doacao);
}
