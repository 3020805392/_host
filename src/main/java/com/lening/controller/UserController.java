package com.lening.controller;

import com.lening.entity.MeunBean;
import com.lening.entity.UserBean;
import com.lening.service.UserService;
import com.lening.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getLogin")
    public String getLogin(UserBean ub){
        return "ok";
    }

    @RequestMapping("getMeunList")
    public List<MeunBean> getMeunList(){
        return userService.getMeunList();
    }

    @RequestMapping("/getUserList")
    public List<UserBean> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping("/getUserListConn")
    public Page<UserBean> getUserListConn(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize){
        UserBean ub = new UserBean();
        ub.setUname("a");
        ub.setAge(12);
        return userService.getUserListConn(ub,pageNum,pageSize);
    }
}
