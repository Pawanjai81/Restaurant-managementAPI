package com.geekster.restaurantManagementAPI.controller;

import com.geekster.restaurantManagementAPI.Models.DTO.SignInInput;
import com.geekster.restaurantManagementAPI.Models.DTO.SignUpOutput;
import com.geekster.restaurantManagementAPI.Models.FoodItem;
import com.geekster.restaurantManagementAPI.Models.Orders;
import com.geekster.restaurantManagementAPI.Models.User;
import com.geekster.restaurantManagementAPI.Service.AuthenticationService;
import com.geekster.restaurantManagementAPI.Service.FoodService;
import com.geekster.restaurantManagementAPI.Service.OrderService;
import com.geekster.restaurantManagementAPI.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FoodService foodService;

    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("user/SignIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }
    @PostMapping("order")
    public String addOrder(@RequestBody Orders order, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email,token)) {
            boolean foodOrdered = orderService.addOrder(order,email);

            return  foodOrdered?"Food ordered":"The food you are trying to order is Not available in the menu";

        }
        else {
            return "Sign in to order Food";
        }
    }

    @GetMapping("foods")
    public List<FoodItem> getAllFoodItems()
    {
        return foodService.getAllFoodItems();
    }
}
