package com.geekster.restaurantManagementAPI.Repo;

import com.geekster.restaurantManagementAPI.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepo extends JpaRepository<Token,Long> {
    Token findFirstByTokenValue(String authTokenValue);
}
