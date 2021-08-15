package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.dto.NewUser;
import edu.miu.cs545.project.onlinestore.dto.NewUserDTO;
import edu.miu.cs545.project.onlinestore.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/signup")
public class RegistrationController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public String register(@RequestBody NewUserDTO user){
        return userDetailsService.signUpUser(user);
    }
}
