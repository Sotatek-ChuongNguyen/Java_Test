package com.example.retail.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionRequest {

    private int customerId;

    private int productId;

    private int quantity;

    private float price;

}
