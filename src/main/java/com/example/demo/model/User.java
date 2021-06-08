package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//The class tells us the structure of a  general document in the user collection of the
//connected DB.
@Document(collection = "Users")
public class User {
    @Id
    // MongoDB will now automatically generate id strings for us
    private String id;
    private String name;
    private double age;
    public User(){
        super();
    }
    public User(String name, double age){
        super();
        this.age = age;
        this.name = name;
    }
    public User(String name){
        super();
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
