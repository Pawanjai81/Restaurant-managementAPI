package com.geekster.restaurantManagementAPI.Service;

import com.geekster.restaurantManagementAPI.Models.FoodItem;
import com.geekster.restaurantManagementAPI.Models.Orders;
import com.geekster.restaurantManagementAPI.Models.User;
import com.geekster.restaurantManagementAPI.Repo.IOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    IOrdersRepo iOrderRepo;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    public boolean addOrder(Orders order, String email) {
        FoodItem food = order.getFood();

        boolean foodAvail = foodService.isFoodInTheMenu(food);
        User user = userService.findFirstByUserEmail(email);
        if(foodAvail)
        {
            order.setCustomer(user);
            iOrderRepo.save(order);
            return true;
        }
        else
            return false;


    }
}
