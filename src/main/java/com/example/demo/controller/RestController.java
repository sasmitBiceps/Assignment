package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    UserRepository repo;
    @Autowired
    ItemRepository itemrepo;
    @GetMapping("/fetch/{id}")
    public Optional<User> fetch(@PathVariable String id){
        return repo.findById(id);
    }
    @PostMapping("/create")
    public User create(@RequestBody User user){
        User in = repo.insert(user);
        return in;
    }
    @GetMapping("/fetchItem/{id}")
    public String fetchItem(@PathVariable String id){
        Optional<Item> it = itemrepo.findById(id);
        if(it.isPresent()) return "Successful";
        return  "Unsuccessful";
    }
    @PostMapping("/newItem")
    public Item post(@RequestBody Item item){
        Item it = itemrepo.insert(item);
        return item;
    }
}
