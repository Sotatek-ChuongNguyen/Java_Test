package com.example.retail.service.impl;

import com.example.retail.entity.Customer;
import com.example.retail.entity.Inventory;
import com.example.retail.entity.RetailBalance;
import com.example.retail.repository.CustomerRepository;
import com.example.retail.repository.InventoryRepository;
import com.example.retail.repository.RetailRepository;
import com.example.retail.request.TransactionRequest;
import com.example.retail.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final RetailRepository retailRepository;

    private final InventoryRepository inventoryRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, RetailRepository retailRepository, InventoryRepository inventoryRepository) {
        this.customerRepository = customerRepository;
        this.retailRepository = retailRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void completeTransaction(TransactionRequest transactionRequest) {
        Customer customer = customerRepository.getById(transactionRequest.getCustomerId());
        float totalAmount = transactionRequest.getQuantity() * transactionRequest.getPrice();
        float newAmount = customer.getBalance() - totalAmount;
        // save it again
        customer.setBalance(newAmount);
        customerRepository.save(customer);
        // add to retail account
        RetailBalance retailBalance = retailRepository.getById(1);
        retailBalance.setAmount(retailBalance.getAmount() + totalAmount);
        retailRepository.save(retailBalance);
        // update product quantity in inventory
        Inventory inventory = inventoryRepository.getById(transactionRequest.getProductId());
        inventory.setQuantity(inventory.getQuantity() - transactionRequest.getQuantity());
        inventoryRepository.save(inventory);
    }

    @Override
    public void doDeposit(int customerId, float amount) {
        Customer customer = customerRepository.getById(customerId);
        // set new deposit account amount
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);
    }
}
