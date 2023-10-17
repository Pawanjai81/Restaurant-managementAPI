package com.geekster.restaurantManagementAPI.Repo;

import com.geekster.restaurantManagementAPI.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepo extends JpaRepository<Orders, Integer> {
}
