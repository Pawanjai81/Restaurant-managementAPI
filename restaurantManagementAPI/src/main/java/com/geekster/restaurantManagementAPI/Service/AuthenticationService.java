package com.geekster.restaurantManagementAPI.Service;

import com.geekster.restaurantManagementAPI.Models.Token;
import com.geekster.restaurantManagementAPI.Repo.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationTokenRepo iAuthenticationRepo;

    public void saveAuthToken(Token auth) {
        iAuthenticationRepo.save(auth);
    }

    public boolean authenticate(String email, String authTokenValue)
    {
        Token authToken = iAuthenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

}
