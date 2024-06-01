package com.naher_farhsa.ATMSpringboot;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
class AtmService {

    private int PIN ;
    private double balance;
    public ResponseEntity<String> withdraw(double amount,int PIN) {
        if (this.PIN==PIN) {
            if (amount <= 0) {
                return ResponseEntity.badRequest().body("Invalid amount!");
            }
            if (amount > balance) {
                return ResponseEntity.badRequest().body("Insufficient balance!");
            }
            balance -= amount;
            return ResponseEntity.ok("Amount withdrawn: " + amount + " Successfully!");
        }
        else
            return ResponseEntity.ok("You entered wrong PIN! "+PIN);

    }

    public ResponseEntity<String> deposit(double amount,int PIN) {
       if (this.PIN==PIN){
           if (amount <= 0) {
                return ResponseEntity.badRequest().body("Invalid amount!");
            }
            balance += amount;
            return ResponseEntity.ok("Amount deposited: " + amount+" Successfully!");
        }
        else
           return  ResponseEntity.ok("You entered wrong PIN! "+PIN);
    }

    public ResponseEntity<Double> checkBalance(int PIN) {
       if(this.PIN==PIN)
        return ResponseEntity.ok(balance);
       else
           System.out.print("You entered wrong PIN! ");
           return ResponseEntity.ok((double)PIN);
    }

    public ResponseEntity<String> setInitialBalance(double initialBalance) {
        this.balance = initialBalance;
        return ResponseEntity.ok("Your initial balance : " + initialBalance);
    }

    public ResponseEntity<String> setPIN(int PIN) {
        this.PIN =PIN ;
        return ResponseEntity.ok("Your PIN : " + PIN);
    }
}



