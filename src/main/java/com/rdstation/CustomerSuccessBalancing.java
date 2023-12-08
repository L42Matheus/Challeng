package com.rdstation;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerSuccessBalancing {

    private final List<CustomerSuccess> customerSuccessList;
    private final List<Customer> customerList;
    private final List<Integer> customerSuccessAway;

    public CustomerSuccessBalancing(List<CustomerSuccess> customerSuccessList,
                                    List<Customer> customerList,
                                    List<Integer> customerSuccessAway) {
        this.customerSuccessList = customerSuccessList;
        this.customerList = customerList;
        this.customerSuccessAway = customerSuccessAway;
    }


    public int balanceCustomersWithSuccessManagers() {
        List<CustomerSuccess> availablesCustomerSuccess = getAvailableCustomerSuccess();

        if (availablesCustomerSuccess.isEmpty() || customerList.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> csToCUstinerCount = new HashMap<>();

        int csIndex = 0;

        for (Customer customer: customerList){
            if (availablesCustomerSuccess.get(csIndex).getScore() >= customer.getScore()) {
                break;
            }

            csToCUstinerCount.put(availablesCustomerSuccess.get(csIndex).getId(),
                    csToCUstinerCount.getOrDefault(availablesCustomerSuccess.get(csIndex).getId(), 0) + 1);
        }

        int maxCustomers = 0;
        int maxCsId = 0;

        for (Map.Entry<Integer, Integer> entry : csToCUstinerCount.entrySet()) {
            if (entry.getValue() > maxCustomers) {
                maxCustomers = entry.getValue();
                maxCsId = entry.getKey();
            } else if (entry.getValue() == maxCustomers) {
                maxCsId = 0;
            }
        }

        return maxCsId;

    }

    private List<CustomerSuccess> getAvailableCustomerSuccess() {
        return customerSuccessList.stream()
                .filter(cs -> !customerSuccessAway.contains(cs.getId()))
                .collect(Collectors.toList());
    }
}
