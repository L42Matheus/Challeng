package com.rdstation;

import java.util.*;

public class CustomerSuccessBalancing {

    private final List<CustomerSuccess> customerSuccess;
    private final List<Customer> customerList;
    private final List<Integer> customerSuccessAway;

    public CustomerSuccessBalancing(List<CustomerSuccess> customerSuccess,
                                    List<Customer> customerList,
                                    List<Integer> customerSuccessAway) {
        this.customerSuccess = customerSuccess;
        this.customerList = customerList;
        this.customerSuccessAway = customerSuccessAway;
    }


    public int run() {
        List<CustomerSuccess> availablesCustomerSuccess = getAvailableCustomerSuccess();


        return 0;
    }

    private List<CustomerSuccess> getAvailableCustomerSuccess() {
        return null;
    }
}
