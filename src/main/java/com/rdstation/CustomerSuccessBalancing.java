package com.rdstation;

import java.util.*;

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


    public int run() {
        List<CustomerSuccess> availablesCustomerSuccess = getAvailableCustomerSuccess();


        return 0;
    }

    private List<CustomerSuccess> getAvailableCustomerSuccess() {
        return customerSuccessList;
    }
}
