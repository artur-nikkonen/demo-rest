package com.flamexander.demo.rest.services;

import com.flamexander.demo.rest.entities.Client;
import com.flamexander.demo.rest.entities.Item;
import com.flamexander.demo.rest.exceptions.ClientNotFoundException;
import com.flamexander.demo.rest.exceptions.ResourceNotFoundException;
import com.flamexander.demo.rest.repositories.ClientRepository;
import com.flamexander.demo.rest.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOneById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item [id = " + id + "] not found"));
    }

    public Item createOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    public boolean isItemExistsById(Long id) {
        return itemRepository.existsById(id);
    }
}
