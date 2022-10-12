package com.example.retail.service.impl;

import com.example.retail.entity.Inventory;
import com.example.retail.entity.Order;
import com.example.retail.repository.InventoryRepository;
import com.example.retail.repository.OrderRepository;
import com.example.retail.repository.RetailRepository;
import com.example.retail.request.ProductRequest;
import com.example.retail.service.RetailService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetailServiceImpl implements RetailService {

    private final InventoryRepository inventoryRepository;

    private final OrderRepository orderRepository;

    private final RetailRepository retailRepository;

    private final ModelMapper modelMapper;

    public RetailServiceImpl(InventoryRepository inventoryRepository, OrderRepository orderRepository, RetailRepository retailRepository, ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.orderRepository = orderRepository;
        this.retailRepository = retailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewProduct(ProductRequest productRequest) {
        Inventory inventory = modelMapper.map(productRequest, Inventory.class);
        inventoryRepository.save(inventory);
    }

    @Override
    public void addNewQuantityForOldProduct(int productId, int quantity) {
        Inventory inventory = inventoryRepository.getById(productId);
        // set new quantity
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void checkValue() {
        float totalRevenue = 0;
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            totalRevenue += order.getTotalExpense();
        }
        float totalRetailAccountAmount = retailRepository.getById(1).getAmount();
        if(totalRetailAccountAmount == totalRevenue){
            // do nothing
        }else {
            // call some service to send email notification
        }
    }
}
