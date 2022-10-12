package com.example.retail.resource;

import com.example.retail.constant.ErrorConstant;
import com.example.retail.request.TransactionRequest;
import com.example.retail.response.ApiResponse;
import com.example.retail.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/complete-transaction")
    public ResponseEntity<?> completeTransaction(@RequestBody TransactionRequest transactionRequest){
        try{
            customerService.completeTransaction(transactionRequest);
            return ResponseEntity.ok().body(new ApiResponse<>(null, null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, ErrorConstant.ERR_000));
        }
    }

    @PostMapping("/do-deposit")
    public ResponseEntity<?> doDeposit(@RequestParam int customerId,  @RequestParam float amount){
        try{
            customerService.doDeposit(customerId, amount);
            return ResponseEntity.ok().body(new ApiResponse<>(null, null));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, ErrorConstant.ERR_000));
        }
    }

}
