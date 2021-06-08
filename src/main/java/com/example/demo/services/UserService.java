package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.util.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String id){
        return userRepository.findById(id);
    }

    public Response fetchUser(String id)
    {
        Response response = new Response();
        Optional<User> user = userRepository.findById(id);
        User user1 = null;
        if(user.isPresent())
        {
            user1 = user.get();
            response.setSuccess(true);
            response.setResponse("User present in database");
            response.setHttpStatus(HttpStatus.OK);
        }
        else
        {
            response.setSuccess(false);
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponse("User not present with the given id : " + id);
        }

        System.out.println("User fetched : " + user1);
        return response;
    }

    public Response insertUser(User user){
        User insertedUser = userRepository.insert(user);
        Response response = new Response();
        Optional<User> success = userRepository.findById(insertedUser.getId());
        if(success.isPresent()){
            response.setSuccess(true);
            response.setResponse("User created successfully");
            response.setHttpStatus(HttpStatus.OK);
        }
        else{
            response.setSuccess(false);
            response.setResponse("User couldn't be created");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
