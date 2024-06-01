package com.naher_farhsa.ATMSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//We are AtmController to  access endpoint through RestClient
@RestController
@RequestMapping("/naher_farhsa/account")
public class AtmController {
    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam double amount, int PIN) {
        return atmService.withdraw(amount,PIN);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam double amount,int PIN) {
        return atmService.deposit(amount,PIN);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> checkBalance(int PIN) {
        return atmService.checkBalance(PIN);
    }

    @PostMapping("/setInitialBalance")
    public ResponseEntity<String> setInitialBalance(@RequestParam double initialBalance) {
        return atmService.setInitialBalance(initialBalance);
    }
    @PostMapping("/setPIN")
    public ResponseEntity<String> setPIN(@RequestParam int PIN) {
        return atmService.setPIN(PIN);
    }
}