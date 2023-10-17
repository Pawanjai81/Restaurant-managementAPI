package com.geekster.restaurantManagementAPI.Repo;

import com.geekster.restaurantManagementAPI.Models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IFoodItemsRepo extends JpaRepository<FoodItem,Integer> {
}
