package org.sample.shop.common.web.controller.v1;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.service.UserService;
import org.sample.shop.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        userService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(int type, String username, String password) {
        ServiceResult result = userService.saveUser(type, username, password);
        if (result.isSuccess()) return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(JsonUtils.fail(result.getError()), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUser(String username, String password) {
        ServiceResult result = userService.getUser(username, password);
        if (result.isSuccess()) return new ResponseEntity<>(result.getData(), HttpStatus.OK);
        return new ResponseEntity<>(JsonUtils.fail(result.getError()), HttpStatus.NOT_FOUND);
    }
}
