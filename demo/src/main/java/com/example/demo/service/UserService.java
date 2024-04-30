package com.example.demo.service;

import com.example.demo.api.UserResponse;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(User user)
    {
            if(userRepository.existsByEmailid(user.getEmailid()))
            {
                return false;
            }

            userRepository.save(user);
            return true;
    }

    public int login(String emailid, String password)
    {
        if(!userRepository.existsByEmailid(emailid))
        {
            return -1;
        }

        User user = userRepository.findByEmailid(emailid);

        if(user.getPassword().equals(password)) return 1;
        return 0;
    }

    public User getUserById(Integer id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserResponse> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users)
        {
            UserResponse userResponse = new UserResponse(
                    user.getName(),
                    user.getId(),
                    user.getEmailid()
                    );

            userResponses.add(userResponse);
        }
        return userResponses;
    }


}
