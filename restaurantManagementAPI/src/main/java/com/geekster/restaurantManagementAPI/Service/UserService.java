package com.geekster.restaurantManagementAPI.Service;

import com.geekster.restaurantManagementAPI.Models.DTO.SignInInput;
import com.geekster.restaurantManagementAPI.Models.DTO.SignUpOutput;
import com.geekster.restaurantManagementAPI.Models.Token;
import com.geekster.restaurantManagementAPI.Models.User;
import com.geekster.restaurantManagementAPI.Repo.IUserRepo;
import com.geekster.restaurantManagementAPI.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;
    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = "";

        String newEmail = user.getUserEmail();

        if(newEmail==null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Please enter A valid Email";

            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser!=null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Email already Registered";

            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        try
        {
            String encryptedPassword = PasswordEncryptor.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            signUpStatusMessage = "New User Registered";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        catch (Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        }

    }

    public String signInUser(SignInInput signInInput) {

        String userEmail = signInInput.getEmail();
        String signInStatusMessage = "";
        if(userEmail ==null)
        {
            signInStatusMessage = "Please Enter a Valid Email";
            return signInStatusMessage;
        }


        User existingUser = userRepo.findFirstByUserEmail(signInInput.getEmail());

        if(existingUser==null)
        {
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }
        //Matching Credentials
        try{
            String encryptedPass = PasswordEncryptor.encryptPassword(signInInput.getPassword());

            if(existingUser.getUserPassword().equals(encryptedPass))
            {
                Token auth = new Token(existingUser);
                authenticationService.saveAuthToken(auth);

                // EmailHandler.sendEmail("tanishtgupta42@gmail.com","Authentication",auth.getTokenValue());
                signInStatusMessage = "Signed In Successfully";
                return signInStatusMessage;
            }
            else
            {
                signInStatusMessage = "Invalid Credentials";
                return  signInStatusMessage;
            }
        }
        catch (Exception e)
        {
            signInStatusMessage = "Internal error occurred during Sign in";
            return  signInStatusMessage;
        }
    }
    public User findFirstByUserEmail(String email) {
        return userRepo.findFirstByUserEmail(email);
    }
}
