package com.thasuniinduma.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.StockDto;
import com.thasuniinduma.backend.entity.Stock;

@Service
public interface StockService {

    Stock createStock(StockDto stockDto);
    Stock updateStock(Long stockId, StockDto stockDto);
    List<Stock> getStockByItemId(Long itemId);
    
}
