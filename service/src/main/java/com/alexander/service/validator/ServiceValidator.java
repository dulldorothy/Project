package com.alexander.service.validator;


import com.alexander.domain.entity.Lot;
import com.alexander.domain.entity.User;
import com.alexander.service.exeption.ServiceException;

import static com.alexander.domain.fields.UserFields.EMPTY_STRING;

public class ServiceValidator {
    //TODO reform
    public static void validate(User user) throws ServiceException {
        if (EMPTY_STRING.equals(user.getPassword()) ||
                EMPTY_STRING.equals(user.getUserName()) ||
                EMPTY_STRING.equals(user.getRole()) ||
                EMPTY_STRING.equals(user.getLastName()) ||
                EMPTY_STRING.equals(user.getEmail()) ||
                EMPTY_STRING.equals(user.getFirstName())) {
            throw new ServiceException("Validation failed");
        }

    }

    public static void validate(int... number) throws ServiceException {
        for (int i : number
        ) {
            if (i < 0) {
                throw new ServiceException("Validation failed");
            }
        }
    }


    public static void validate(String... string) throws ServiceException {
        for (String str : string
        ) {
            if (EMPTY_STRING.equals(str)) {
                throw new ServiceException("Validation failed");
            }

        }
        if (EMPTY_STRING.equals(string)) {
            throw new ServiceException("Validation failed");
        }
    }

    public static void validate(Lot lot) throws ServiceException {
        if (EMPTY_STRING.equals(lot.getTitle()) ||
                lot.getPrice() < 0 ||
                EMPTY_STRING.equals(lot.getStatus()) ||
                EMPTY_STRING.equals(lot.getDescription()) ||
                EMPTY_STRING.equals(lot.getImage()) ||
                EMPTY_STRING.equals(lot.getTagList()) ||
                lot.getDescription() == null ||
                lot.getImage() == null ||
                lot.getStatus() == null ||
                lot.getTitle() == null ||
                lot.getTagList() == null
        ) {

            throw new ServiceException("Validation failed");
        }
    }
}
