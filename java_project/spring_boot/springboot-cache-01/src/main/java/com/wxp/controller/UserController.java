package com.wxp.controller;

import com.wxp.pojo.User;
import com.wxp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{uid}", method = RequestMethod.GET)
    public User getById(@PathVariable("uid") int uid) {
        return userService.findById(uid);
    }
}
