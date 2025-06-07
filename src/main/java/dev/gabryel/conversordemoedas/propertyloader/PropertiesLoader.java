package dev.gabryel.conversordemoedas.propertyloader;

import dev.gabryel.conversordemoedas.propertyloader.exceptions.PropertiesFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final String PROPERTIES_FILE = "application.properties";
    private Properties properties;

    public PropertiesLoader(){
        this.properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            throw new PropertiesFileNotFoundException("Não foi possível carregar o arquivo " + PROPERTIES_FILE);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
