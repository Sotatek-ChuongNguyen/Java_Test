package com.example.retail.resource;

import com.example.retail.constant.ErrorConstant;
import com.example.retail.request.OrderRequest;
import com.example.retail.request.ProductRequest;
import com.example.retail.response.ApiResponse;
import com.example.retail.service.RetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RetailResource {

    private final RetailService retailService;

    public RetailResource(RetailService retailService) {
        this.retailService = retailService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest){
        try{
            retailService.addNewProduct(productRequest);
            return ResponseEntity.ok().body(new ApiResponse<>(null, null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, ErrorConstant.ERR_000));
        }
    }

    @PutMapping("/add-quantity-product")
    public ResponseEntity<?> addQuantityForOldProduct(@RequestParam int productId, @RequestParam int quantity){
        try{
            retailService.addNewQuantityForOldProduct(productId, quantity);
            return ResponseEntity.ok().body(new ApiResponse<>(null, null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, ErrorConstant.ERR_000));
        }
    }

}
