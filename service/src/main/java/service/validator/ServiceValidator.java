package service.validator;

import service.entity.User;
import dao.entity.Lot;


public class ServiceValidator {
    //TODO reform
    public static boolean validate(dao.entity.User user) {
        return (!"".equals(user.getPassword()) &&
                !"".equals(user.getUserName()) &&
                !"".equals(user.getRole()) &&
                !"".equals(user.getLastName()) &&
                !"".equals(user.getFirstName()));
    }

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
