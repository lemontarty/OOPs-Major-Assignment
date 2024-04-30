package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");

        int loginStatus = userService.login(email, password);

        // Determine the response message based on the login status
        switch (loginStatus) {
            case -1:
                return new ErrorResponse("User does not exist");
            case 0:
                return new ErrorResponse("Username/Password Incorrect");
            case 1:
                return "Login Successful";
            default:
                return new ErrorResponse("Unknown error occurred");
        }
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody User user) {
        // Create a User object from the request parameters

        // Check if the account already exists
        boolean accountExists = userService.signUp(user);

        // Determine the response message based on the signup status
        if (accountExists) {
            return "Account Creation Successful";
        } else {
            return new ErrorResponse("Forbidden, Account already exists");
        }
    }

//    @GetMapping("/user")
//    public Object getUserDetails(@RequestParam(value = "userID") Integer userID) {
//        User user = userService.getUserById(userID);
//        if (user == null) {
//            return "User does not exist";
//        } else {
//            return user;
//        }
//    }
@GetMapping("/user")
public Object getUserDetails(@RequestParam("userID") Integer userID) {
    User user = userService.getUserById(userID);
    if (user == null) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "User does not exist");
        return errorResponse;
    } else {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", user.getName());
        userDetails.put("userID", user.getId());
        userDetails.put("email", user.getEmailid());
        return userDetails;
    }
}

@GetMapping("/users")
    public Object getUsers()
    {
        return userService.getAllUsers();
    }
}
