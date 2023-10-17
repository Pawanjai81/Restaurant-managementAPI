package com.geekster.restaurantManagementAPI.Service;

import com.geekster.restaurantManagementAPI.Models.FoodItem;
import com.geekster.restaurantManagementAPI.Repo.IFoodItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodService {
    @Autowired
    IFoodItemsRepo iFoodRepo;

    public void addFoodItem(FoodItem food) {
        iFoodRepo.save(food);
    }

    public boolean isFoodInTheMenu(FoodItem food) {

        Integer id = food.getFoodId();

        return iFoodRepo.existsById(id);
    }

    public List<FoodItem> getAllFoodItems() {
        return iFoodRepo.findAll();
    }
}
