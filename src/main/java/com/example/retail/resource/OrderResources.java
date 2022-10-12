package com.example.retail.resource;

import com.example.retail.constant.ErrorConstant;
import com.example.retail.request.OrderRequest;
import com.example.retail.response.ApiResponse;
import com.example.retail.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class OrderResources {

    private final OrderService orderService;

    public OrderResources(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        try{
            orderService.createOrder(orderRequest);
            return ResponseEntity.ok().body(new ApiResponse<>(null, null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, ErrorConstant.ERR_000));
        }
    }

}
