package com.example.retail.service.impl;

import com.example.retail.entity.Customer;
import com.example.retail.entity.Order;
import com.example.retail.repository.OrderRepository;
import com.example.retail.request.OrderRequest;
import com.example.retail.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);

        Customer customer = new Customer();
        customer.setId(orderRequest.getCustomerId());
        order.setCustomer(customer);

        orderRepository.save(order);
    }
}
