package com.microservice.userservice.service;

import com.microservice.userservice.VO.Department;
import com.microservice.userservice.VO.ResponseTemplateVo;
import com.microservice.userservice.entity.User;
import com.microservice.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("inside the saveUser method of userService");
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        log.info("inside the getUserById method of userService");
        return userRepository.findByUserId(userId);
    }


    public ResponseTemplateVo getUserWithDepartment(Long userId) {
        log.info("inside the getUserWithDepartment method of userService");
        ResponseTemplateVo vo = new ResponseTemplateVo();
        User user = findUserById(userId);
        Department department = restTemplate.getForObject("http://localhost:9001/departments/"+user.getDepartmentId()
                                                            ,Department.class);
        vo.setDepartment(department);
        vo.setUser(user);
        return vo;
    }
}
