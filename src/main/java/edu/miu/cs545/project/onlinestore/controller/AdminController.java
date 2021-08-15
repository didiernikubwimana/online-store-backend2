package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/approve")
    public Boolean approveSeller(@RequestParam("seller") Long sellerId){
        return adminService.approveSeller(sellerId);
    }
}
