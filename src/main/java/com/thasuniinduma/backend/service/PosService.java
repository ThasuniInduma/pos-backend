package com.thasuniinduma.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.PosDto;
import com.thasuniinduma.backend.entity.Pos;

@Service
public interface PosService {

    List<Pos> getAllOrders();
    Pos getOrderById(Long id);
    Pos createOrder(PosDto posDto);
    
}
