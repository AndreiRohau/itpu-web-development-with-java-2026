package com.itpu.controller;

import com.itpu.controller.converter.ReqConverter;
import com.itpu.controller.validator.Validator;
import com.itpu.domain.User;
import com.itpu.service.UserService;
import com.itpu.service.UserServiceImplV1;

public class Controller {

    private Validator validator = new Validator();
    private ReqConverter converter = new ReqConverter();
    private UserService userService = new UserServiceImplV1();

    // request entrypoint
    protected void doPost(Object req, Object resp) {
        // validating req
        validator.validate(req);
        // convert req -> User object
        User user = converter.convert(req);

        Long idOfSavedGuy = userService.saveUser(user);
        // respond to some view if model did not
    }
}
