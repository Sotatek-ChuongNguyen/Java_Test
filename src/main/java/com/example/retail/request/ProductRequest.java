package com.example.retail.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    private String productName;

    private float price;

    private int quantity;

    private float totalExpense;

}
