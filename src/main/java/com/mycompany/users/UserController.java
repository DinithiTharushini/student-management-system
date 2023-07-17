package com.mycompany.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService services;
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listusers= services.ListAll();
        model.addAttribute("listusers",listusers);

        return "users";
    }

}
