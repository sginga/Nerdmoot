package com.example.caleb.nerdmoot.objects;

/**
 * Created by caleb on 3/5/2017.
 */

public class Contact {
    private String name;
    private String number;

    public Contact(){

    }

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public void setName(String n){
        name = n;
    }

    public void setNumber(String n){
        number = n;
    }
}