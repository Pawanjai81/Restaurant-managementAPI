package com.geekster.restaurantManagementAPI.controller;

import com.geekster.restaurantManagementAPI.Models.Admin;
import com.geekster.restaurantManagementAPI.Models.DTO.SignUpOutput;
import com.geekster.restaurantManagementAPI.Models.FoodItem;
import com.geekster.restaurantManagementAPI.Service.AdminService;
import com.geekster.restaurantManagementAPI.Service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    FoodService foodService;
    @PostMapping("Admin/signUp")
    public SignUpOutput addAdmin(@RequestBody @Valid Admin admin)
    {
        return adminService.addAdmin(admin);
    }

    @PostMapping("food")
    public String addFoodItem(@RequestBody @Valid FoodItem food , @RequestParam String adminEMail)
    {
        boolean adminOrNot = adminService.ifAdminExistOrNot(adminEMail);

        if(adminOrNot)
        {
            foodService.addFoodItem(food);
            return "New Food Item added to the menu";
        }
        else return  "Unauthorized access ";

    }
}
