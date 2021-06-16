package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.util.responses.ItemResponse;
import com.example.demo.util.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    public ItemResponse placeOrder(String UserID, String ItemID){
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
        else{
            itemResponse.setUserStatus("User identified");
            itemResponse.setItemStatus("Item fetched");
            Item item = requestedItem.orElse(null);
          //  User user = requestUser.orElse(null);
            if(item.getQuantity() == 0){
                itemResponse.setOrderStatus("Item out of stock");
            }
            else{
                try {
                    itemResponse.setOrderStatus("Order placed successfully");
                    item.setQuantity(item.getQuantity() - 1);
                    itemRepository.save(item);
                }
                catch(Exception e) {
                    System.out.println("Failed to order");
                    itemResponse.setOrderStatus("Order failed");
                }
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
