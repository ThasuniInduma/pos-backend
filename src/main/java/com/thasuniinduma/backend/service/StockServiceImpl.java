package com.thasuniinduma.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.StockDto;
import com.thasuniinduma.backend.entity.Item;
import com.thasuniinduma.backend.entity.Stock;
import com.thasuniinduma.backend.repository.StockRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ItemService itemService;

    public StockServiceImpl(StockRepository stockRepository, ItemService itemService){
        this.stockRepository = stockRepository;
        this.itemService = itemService;
    }

    @Override
    public Stock createStock(StockDto stockDto){
        Item item = itemService.getItemById(stockDto.getItemId());
        if(item == null){
            throw new EntityNotFoundException("Item not found");
        }
        Stock stock = new Stock();
        stock.setItem(item);
        stock.setQty(stockDto.getQty());
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long stockId, StockDto stockDto){
        Stock existingStock = stockRepository.findById(stockId)
        .orElseThrow(() -> new EntityNotFoundException("Stock not found"));
        existingStock.setQty(stockDto.getQty());
        return stockRepository.save(existingStock);
    }

    @Override
    public List<Stock> getStockByItemId(Long itemId){
        return stockRepository.findByItemId(itemId);
    }

}