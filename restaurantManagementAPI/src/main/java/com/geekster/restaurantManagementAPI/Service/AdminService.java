package com.geekster.restaurantManagementAPI.Service;

import com.geekster.restaurantManagementAPI.Models.Admin;
import com.geekster.restaurantManagementAPI.Models.DTO.SignUpOutput;
import com.geekster.restaurantManagementAPI.Repo.IAdminRepo;
import com.geekster.restaurantManagementAPI.Service.HashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo iAdminRepo;
    public SignUpOutput addAdmin(Admin admin) {
        String email = admin.getAdminEmail();

        String signUpStatusMessage = "";
        if(email==null)
        {
            signUpStatusMessage = "Enter a Valid Email";
            return new SignUpOutput(false,signUpStatusMessage);
        }

        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(email);

        if(existingAdmin!=null)
        {
            signUpStatusMessage = "Email Already Registered";
            return  new SignUpOutput(false ,signUpStatusMessage );
        }

        try {
            String encryptPass = PasswordEncryptor.encryptPassword(admin.getAdminPassword());
            admin.setAdminPassword(encryptPass);
            iAdminRepo.save(admin);

            signUpStatusMessage = "New Admin Registered";

            return new SignUpOutput(true,signUpStatusMessage);
        }
        catch (Exception e)
        {
            signUpStatusMessage = "Internal error occurred ";

            return new SignUpOutput(false,signUpStatusMessage);
        }

    }




    public boolean ifAdminExistOrNot(String adminEMail) {
        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(adminEMail);

        return existingAdmin!=null;
    }
}
