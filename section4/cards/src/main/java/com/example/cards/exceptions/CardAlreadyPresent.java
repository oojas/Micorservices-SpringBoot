package com.example.cards.exceptions;

public class CardAlreadyPresent extends RuntimeException{
    public CardAlreadyPresent(String message){
        super(message);
    }
}
