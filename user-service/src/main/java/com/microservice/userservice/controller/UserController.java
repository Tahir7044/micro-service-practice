package com.microservice.userservice.controller;


import com.microservice.userservice.VO.ResponseTemplateVo;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("inside the saveUser method of userController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long userId){
        System.out.println(userId);
        log.info("inside the findDepartmentById method of userController");
        return userService.findUserById(userId);
    }

    @GetMapping("/withdepartment/{id}")
    public ResponseTemplateVo getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("inside the getUserWithDepartment method of userController");
        return userService.getUserWithDepartment(userId);
    }

}
