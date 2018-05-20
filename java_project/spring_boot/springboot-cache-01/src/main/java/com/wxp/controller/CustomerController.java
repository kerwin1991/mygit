package com.wxp.controller;

import com.wxp.pojo.Customer;
import com.wxp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/5/15.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/find/{custId}")
    public Customer findById(@PathVariable("custId")int id) {
        return customerService.selectById(id);
    }

    @GetMapping(value = "/update")
    public Customer update(Customer customer) {
        Customer update = customerService.update(customer);
        return update;
    }

    @GetMapping(value = "/delete")
    public String delete(int custId) {
        customerService.deleteById(custId);
        return "SUCCESS";
    }

    @GetMapping(value = "/find/name/{custName}")
    public Customer delete(@PathVariable("custName") String custName) {
        return customerService.queryByName(custName);
    }
}
