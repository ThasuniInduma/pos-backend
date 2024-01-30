package com.thasuniinduma.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.ItemDto;
import com.thasuniinduma.backend.entity.Category;
import com.thasuniinduma.backend.entity.Item;
import com.thasuniinduma.backend.repository.CategoryRepository;
import com.thasuniinduma.backend.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(ItemDto itemDto){
        Category category = categoryRepository.findById(itemDto.getCategoryId()).orElse(null);
        if (category != null) {
            Item item = new Item();
            item.setName(itemDto.getName());
            item.setQty(itemDto.getQty());
            item.setPrice(itemDto.getPrice());
            item.setCategory(category);
            return itemRepository.save(item);
        } else {
            return null;
        }
    }

    @Override
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null); 
    }

    @Override
    public Item updateItem(Long id, Item item){
        Item existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setName(item.getName());
            existingItem.setCategory(item.getCategory());
            existingItem.setQty(item.getQty());
            existingItem.setPrice(item.getPrice());
            return itemRepository.save(existingItem);
        } else {
            return null;
        }
    }

    @Override
    public Item deleteItem(Long id){
        Item existingItem = itemRepository.findById(id).orElse(null);
        if (existingItem != null) {
            itemRepository.deleteById(id);
            return existingItem;
        }
        throw new EntityNotFoundException("There is no item associated with the id"+id);
    }

    @Override
    public List<Item> getItemsByCategory(Long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            return itemRepository.findItemsByCategory(category);
        } else {
            return null;
        }
    }
    
}
