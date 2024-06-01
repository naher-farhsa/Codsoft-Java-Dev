package com.naher_farhsa.ATMSpringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Component
public class AtmConsole implements CommandLineRunner {
    private boolean isRunning;
    private final Scanner scanner = new Scanner(System.in);
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final ConfigurableApplicationContext applicationContext;
    private int PIN;

    @Autowired
    public AtmConsole(RestTemplate restTemplate, ConfigurableApplicationContext applicationContext) {
        this.restTemplate = restTemplate;
        this.baseUrl = "http://localhost:8080/naher_farhsa/account";
        this.applicationContext = applicationContext;
        this.isRunning = true;
    }

    @Override
    public void run(String... args) {
        System.out.println("⭐⭐ Welcome to the ATM ⭐⭐");
        try {
            System.out.print("Please set your PIN: ");
            PIN = Integer.parseInt(scanner.nextLine());
            setPIN(PIN);
            System.out.print("Please set your initial balance: ");
            double initialBalance = Double.parseDouble(scanner.nextLine());
            setInitialBalance(initialBalance);

            while (isRunning) {
                displayOptions();

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> withdraw();
                    case 2 -> deposit();
                    case 3 -> checkBalance();
                    case 4 -> exit();
                    default -> System.out.println("Invalid choice! Please enter a valid option.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid balance entered! ");
            isRunning = false;
            applicationContext.close();
        }
    }


    private void displayOptions() {
        System.out.println("Please select an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private void setInitialBalance(double initialBalance) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + "/setInitialBalance?initialBalance=" + initialBalance, null, String.class);
        System.out.println(responseEntity.getBody());
    }

    private void setPIN(int PIN) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + "/setPIN?PIN=" + PIN, null, String.class);
        System.out.println(responseEntity.getBody());
    }

    private void withdraw() {
        promptPIN();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + "/withdraw?amount=" + amount + "&PIN=" + PIN, null, String.class);
        System.out.println(responseEntity.getBody());
    }

    private void deposit() {
        promptPIN();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + "/deposit?amount=" + amount + "&PIN=" + PIN, null, String.class);
        System.out.println(responseEntity.getBody());
    }

    private void checkBalance() {
        promptPIN();
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(baseUrl + "/balance?PIN=" + PIN, Double.class);
        System.out.println("Your current balance: " + responseEntity.getBody());
    }

    private void promptPIN() {
        System.out.print("Enter your PIN: ");
        PIN = scanner.nextInt();
    }

    private void exit() {
        System.out.println("Exiting the ATM!\n⭐⭐ Goodbye ⭐⭐");
        isRunning = false;
        applicationContext.close();
    }

}
