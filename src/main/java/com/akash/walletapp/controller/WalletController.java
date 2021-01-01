package com.akash.walletapp.controller;


import com.akash.walletapp.entity.Wallet;
import com.akash.walletapp.service.ValidationErrorService;
import com.akash.walletapp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){

        ResponseEntity errors = validationService.validate(result);
        if(errors != null){
            return errors;
        }

        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved, HttpStatus.CREATED);
    }
}