package dev.gabryel.conversorDeMoedas.main.util.propertyLoader.exceptions;

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
