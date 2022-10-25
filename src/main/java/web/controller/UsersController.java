package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService service;


    @GetMapping()
    public String getAllUsers(ModelMap map) {
        map.addAttribute("users", service.listUsers());
        return "users";
    }

    @PostMapping()
    public String addUser(@ModelAttribute User user) {
        service.add(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/test")
    public void testGetUser(){
        User user = service.getUser(1L);
        System.out.println(user.getEmail());
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id")Long id, User user){
        service.editUser(user, id);
        return "redirect:/users";
    }

}


