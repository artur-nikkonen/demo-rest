package com.flamexander.demo.rest.controllers;

import com.flamexander.demo.rest.entities.Item;
import com.flamexander.demo.rest.exceptions.BadRequestException;
import com.flamexander.demo.rest.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable Long id) {
        return itemService.findOneById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        return itemService.createOrUpdateItem(item);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        if (item.getId() == null || !itemService.isItemExistsById(item.getId())) {
            throw new BadRequestException("Item doesn't exists");
        }
        return itemService.createOrUpdateItem(item);
    }
}