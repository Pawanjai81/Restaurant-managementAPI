package com.geekster.restaurantManagementAPI.controller;

import com.geekster.restaurantManagementAPI.Models.FoodItem;
import com.geekster.restaurantManagementAPI.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {
    @Autowired
    FoodService foodService;

    @GetMapping("food")
    public List<FoodItem> getAllFoodItems()
    {
        return foodService.getAllFoodItems();
    }
}
