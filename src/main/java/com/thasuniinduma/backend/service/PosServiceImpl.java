package com.thasuniinduma.backend.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.PosDto;
import com.thasuniinduma.backend.entity.Item;
import com.thasuniinduma.backend.entity.Pos;
import com.thasuniinduma.backend.repository.ItemRepository;
import com.thasuniinduma.backend.repository.PosRepository;

@Service
public class PosServiceImpl implements PosService {

    @Autowired
    private PosRepository posRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Pos> getAllOrders() {
        return posRepository.findAll();
    }

    @Override
    public Pos createOrder(PosDto posDto) {
        Pos pos = new Pos();
        List<Long> items = posDto.getItems();
        Set<Item> itemSet = new HashSet<>();
        pos.setTotal(0.0);

        for (Long itemId : items) {
            Item item = itemRepository.findById(itemId).orElse(null);

            if(item != null && item.getQty() != 0){
                itemSet.add(item);
                pos.setTotal(pos.getTotal()+item.getPrice());
            }
        }
        Double tax = pos.getTotal()*10/100;

        pos.setTax(tax);
        pos.setPosTime(LocalDateTime.now());
        pos.setItems(itemSet);

        return posRepository.save(pos);
    }

    @Override
    public Pos getOrderById(Long id) {
        return posRepository.findById(id).orElse(null);
    }
 
}
