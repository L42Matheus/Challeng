package com.rdstation;

import com.rdstation.model.Customer;
import com.rdstation.model.CustomerSuccess;
import com.rdstation.service.CustomerSuccessBalancing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class CustomerSuccessBalancingTest {
    @Test
    public void givenMultipleCSAndCustomers_OneBestMatch_CS1() {
        List<CustomerSuccess> css = toList(new CustomerSuccess(1, 60),
                                           new CustomerSuccess(2, 20),
                                           new CustomerSuccess(3, 95),
                                           new CustomerSuccess(4, 75)
        );
        List<Customer> customers = toList(new Customer(1, 90),
                                                 new Customer(2, 20),
                                                 new Customer(3, 70),
                                                 new Customer(4, 40),
                                                 new Customer(5, 60),
                                                 new Customer(6, 10));

        List<Integer> csAway = toList(2, 4);

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 1);
    }

    @Test
    public void givenLimitedCSAndCustomers_NoSuitableMatch() {
        List<CustomerSuccess> css = mapCustomerSuccess(11, 21, 31, 3, 4, 5);
        List<Customer> customers = mapCustomers(10, 10, 10, 20, 20, 30, 30, 30, 20, 60);
        List<Integer> csAway = Collections.emptyList();

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 0);
    }

    //"@TODO: Otimiza estruturas de dados: Em vez de usar listas, usar estruturas de dados como tabelas hash "
    //       "para pesquisar com eficiência agentes disponíveis com pontuações apropriadas."
    @Test(timeout=1000)
    public void givenManyCSAndCustomers_ShouldAssignToHighestMatchedCS998() {

        List<CustomerSuccess> css = mapCustomerSuccess(IntStream.range(1, 999).toArray());
        List<Customer> customers =  buildSizeEntities(100000, 998);
        List<Integer> csAway = toList(999);

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 998);
    }

    @Test
    public void givenCSAndCustomers_NoCSMatchesCustomers() {
        List<CustomerSuccess> css = mapCustomerSuccess(1, 2, 3, 4, 5, 6);
        List<Customer> customers = mapCustomers(10, 10, 10, 20, 20, 30, 30, 30, 20, 60);
        List<Integer> csAway = Collections.emptyList();

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 0);
    }

    @Test
    public void givenCSAndCustomers_SingleBestMatch_CS1() {
        List<CustomerSuccess> css = mapCustomerSuccess(100, 2, 3, 6, 4, 5);
        List<Customer> customers = mapCustomers(10, 10, 10, 20, 20, 30, 30, 30, 20, 60);
        List<Integer> csAway = Collections.emptyList();

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 1);
    }

    @Test
    public void givenUnavailableCS_ShouldNotAssignAnyCustomers() {
        List<CustomerSuccess> css = mapCustomerSuccess(100, 99, 88, 3, 4, 5);
        List<Customer> customers = mapCustomers(10, 10, 10, 20, 20, 30, 30, 30, 20, 60);
        List<Integer> csAway = toList(1, 3, 2);

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 0);
    }

    @Test
    public void givenCSOnLeave_ShouldAssignCustomersToNextBestMatch_CS3() {
        //Should assign customers to the next available best matched Customer Success (CS3)
        List<CustomerSuccess> css = mapCustomerSuccess(100, 99, 88, 3, 4, 5);
        List<Customer> customers = mapCustomers(10, 10, 10, 20, 20, 30, 30, 30, 20, 60);
        List<Integer> csAway = toList(4,5,6);

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 3);
    }

    @Test
    public void givenPartialCSUnavailable_ShouldAssignCustomersToNextBestMatch_CS1() {
        List<CustomerSuccess> css = mapCustomerSuccess(60, 40, 95, 75);
        List<Customer> customers = mapCustomers(90, 70, 20, 40, 60, 10);
        List<Integer> csAway = toList(2,4);

        assertEquals(new CustomerSuccessBalancing(css, customers, csAway).balanceCustomersWithSuccessManagers(), 1);
    }

    private List<CustomerSuccess> mapCustomerSuccess(int... scores) {
        List<CustomerSuccess> entities = new ArrayList<>(scores.length);
        for (int i = 0; i < scores.length; i++) {
            entities.add(new CustomerSuccess(i + 1, scores[i]));
        }
        return entities;
    }

    private List<Customer> mapCustomers(int... scores) {
        List<Customer> entities = new ArrayList<>(scores.length);
        for (int i = 0; i < scores.length; i++) {
            entities.add(new Customer(i + 1, scores[i]));
        }
        return entities;
    }

    private List<Customer> buildSizeEntities(int size, int score) {
        List<Customer> entities = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            entities.add(new Customer(i + 1, score));
        }
        return entities;
    }


    private <T> List<T> toList(T... values) {
        return Arrays.stream(values).collect(Collectors.toList());
    }


}