package com.example.retail.service;

import com.example.retail.request.TransactionRequest;

public interface CustomerService {

    void completeTransaction(TransactionRequest transactionRequest);

    void doDeposit(int customerId, float amount);

}
