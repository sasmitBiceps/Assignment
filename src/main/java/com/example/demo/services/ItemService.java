package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.util.responses.ItemResponse;
import com.example.demo.util.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    public ItemResponse placeOrder(String UserID, String ItemID, int quantity){
        Optional<User> requestUser = userService.getUser(UserID);
        Optional<Item> requestedItem = itemRepository.findById(ItemID);
        ItemResponse itemResponse = new ItemResponse();
        if(!requestUser.isPresent()){
            itemResponse.setUserStatus("User not present in database");
            itemResponse.setOrderStatus("Order couldn't be placed");
        }
        else if(!requestedItem.isPresent()){
            itemResponse.setItemStatus("Item not present in database");
            itemResponse.setOrderStatus("Order couldn't be placed");
        }
        Item item = requestedItem.orElse(new Item());
        itemResponse.setItemStatus("Item fetched!");
        itemResponse.setUserStatus("User identified");
        if(quantity > item.getQuantity()){
            itemResponse.setOrderStatus("Order failed! We only have " + item.getQuantity() + " items.");
        }
        else{
            try {
                itemResponse.setOrderStatus("Order placed successfully");
                item.setQuantity(item.getQuantity() - quantity);
                itemRepository.save(item);
            }
            catch(OptimisticLockingFailureException e) {
                System.out.println("Race condition occurred!");
                itemResponse.setOrderStatus("Order failed");
            }
            catch (Exception e){
                System.out.println("Some error occurred, please try again.");
                itemResponse.setUserStatus("Order failed");
            }
        }
        return itemResponse;
    }

    public Response insert(Item item){
        Response response = new Response();
        Item insertedItem = itemRepository.insert(item);
        Optional<Item> success = itemRepository.findById(insertedItem.getId());
        if(success.isPresent()){
            response.setSuccess(true);
            response.setResponse("Item created successfully");
            response.setHttpStatus(HttpStatus.OK);
        }
        else{
            response.setSuccess(false);
            response.setResponse("Item couldn't be created");
           response.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
