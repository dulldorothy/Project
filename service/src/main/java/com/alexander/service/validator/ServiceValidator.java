package com.alexander.service.validator;


import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.User;

public class ServiceValidator {
    //TODO reform
    public static boolean validate(User user) {
        return (!"".equals(user.getPassword()) &&
                !"".equals(user.getUserName()) &&
                !"".equals(user.getRole()) &&
                !"".equals(user.getLastName()) &&
                !"".equals(user.getFirstName()));
    }


    public static boolean validate(String string) {
        return !"".equals(string);
    }

    public static boolean validate(Lot lot) {
        return (!"".equals(lot.getTitle()) && !"".equals(lot.getPrice()) && !"".equals(lot.getStatus()));
    }
}
