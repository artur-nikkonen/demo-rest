package com.flamexander.demo.rest.repositories;

import com.flamexander.demo.rest.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
