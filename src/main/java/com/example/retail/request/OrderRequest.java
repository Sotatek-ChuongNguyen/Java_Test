package com.example.retail.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {

    private int customerId;

    private String sku;

    private int productNum;

}
