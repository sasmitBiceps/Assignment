package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import com.example.demo.util.responses.ItemResponse;
import com.example.demo.util.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    //Autowired so we don't have to create objects by our own, spring will provide them
    // to us.
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping("/fetch/{id}")
    // Get mapping indicates the type of request we will get on this link ,"GET" in this case.
    public Response fetch(@PathVariable String id) throws Exception {
        if (!"".equals(id))
        {
            return userService.fetchUser(id);
        }
        else
        {
            System.err.println("Null user id");
            Response response = new Response();
            response.setSuccess(false);
            response.setResponse("Null user id");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }


    @PostMapping("/create")
    // Post mapping indicates the type of request we will get on this link, "POST" in this case.
    public Response create(@RequestBody User user){
        if(user != null) {
            Response in = userService.insertUser(user);
            return in;
        }
        else{
            System.err.println("Null user");
            Response response = new Response();
            response.setSuccess(false);
            response.setResponse("Null user");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("/placeOrder")
    public ItemResponse placeOrder(@RequestParam(name = "UserID") String UserID, @RequestParam(name = "ItemID") String ItemID, @RequestParam(name = "Quantity") int quantity){
        ItemResponse response = new ItemResponse();
        if("".equals(UserID) && "".equals(ItemID)){
            response.setItemStatus("ItemID not present");
            response.setUserStatus("UserID not present");
            response.setOrderStatus("Order not placed");
        }
        else if("".equals(UserID)){
            response.setUserStatus("UserID not present");
            response.setOrderStatus("Order not placed");
        }
        else if("".equals(ItemID)){
            response.setItemStatus("ItemID not present");
            response.setOrderStatus("Order not placed");
        }
        else if(quantity <= 0){
            response.setItemStatus("Quantity should be greater than 0");
            response.setOrderStatus("Order not placed");
        }
        else{
            response = itemService.placeOrder(UserID, ItemID, quantity);
        }
        return  response;
    }

    @PostMapping("/newItem")
    public Response post(@RequestBody Item item){
        Response response = new Response();
        if(item == null){
            System.err.println("Null item");
            Response userResponse = new Response();
            userResponse.setSuccess(false);
            userResponse.setResponse("Null item");
            userResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else {
            response = itemService.insert(item);
        }
        return response;
    }
}
//handling the race condition while placing an  order