package dev.gabryel.conversordemoedas.propertyloader.exceptions;

public class PropertiesFileNotFoundException extends RuntimeException {
    public PropertiesFileNotFoundException(String message){
        super(message);
    }
}
