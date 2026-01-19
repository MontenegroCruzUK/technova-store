package com.montenegro.technova.exeption;

public class ResourceNotFoundException extends RuntimeException{

 public ResourceNotFoundException(String message){
   super(message);
 }
}
