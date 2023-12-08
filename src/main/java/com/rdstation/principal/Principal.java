package com.rdstation.principal;

import com.rdstation.model.Customer;
import com.rdstation.model.CustomerSuccess;
import com.rdstation.service.CustomerSuccessBalancing;

import java.util.Arrays;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        List<CustomerSuccess> css = Arrays.asList(
                new CustomerSuccess(1, 60),
                new CustomerSuccess(2, 20),
                new CustomerSuccess(3, 95),
                new CustomerSuccess(4, 75)
        );

        List<Customer> customers = Arrays.asList(
                new Customer(1, 90),
                new Customer(2, 20),
                new Customer(3, 70),
                new Customer(4, 40),
                new Customer(5, 60),
                new Customer(6, 10)
        );

        List<Integer> csAway = Arrays.asList(2, 4);

        int result = new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers();
        System.out.println("O id do CS que atende 4 clientes: " + result);
    }
}
