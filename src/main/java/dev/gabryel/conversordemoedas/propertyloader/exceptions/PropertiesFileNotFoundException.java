package dev.gabryel.conversordemoedas.propertyloader.exceptions;

public class PropertiesFileNotFoundException extends RuntimeException {
    private String message;

    public PropertiesFileNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
