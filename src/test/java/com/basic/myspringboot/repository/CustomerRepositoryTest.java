package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Rollback(value = false)
    void testDeleteCustomer() {
        Customer customer = customerRepository.findById(10L) //없으면 에러
                .orElseThrow(()-> new RuntimeException("Customer Not Found"));
        customerRepository.delete(customer);
    }

    @Test
    @Rollback(value = false)
    void testUpdateCustomer() {
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        // 수정하려면 setter method 호출
        customer.setCustomerName("SpringBoot");
        //customerRepository.save(customer); //save 안해도 dirty read (@Transactional)
        assertThat(customer.getCustomerName()).isEqualTo("SpringBoot");
    }


    @Test
    void testByNotFoundException (){
        Customer customer = customerRepository.findByCustomerId("A004")
                .orElseThrow(()-> new RuntimeException("Customer Not Found"));
        //assertThat(customer.getCustomerId()).isEqualTo("A004");
    }

    @Test
    @Disabled
    void testFindBy(){
        Optional<Customer>  optionalCustomer = customerRepository.findById(1L);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            assertThat(customer.getId()).isEqualTo(1L);
        }
        //assertThat(optionalCustomer).isNotEmpty();
    }



    @Test
    @Rollback(value = false)
    @Disabled
    void testCreateCustomer() {
        //Given (준비 단계)
        Customer customer = new Customer();
//        customer.setCustomerId("A001");
//        customer.setCustomerName("스프링");
        customer.setCustomerId("A002");
        customer.setCustomerName("스프링");
        //When (실행 단계)
        Customer addCustomer = customerRepository.save(customer);
        //Then (검증 단계)
        assertThat(addCustomer).isNotNull();
        //assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링2");
    }


}