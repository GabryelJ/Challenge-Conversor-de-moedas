package dev.gabryel.conversorDeMoedas.main.util.propertyLoader;

import dev.gabryel.conversorDeMoedas.main.util.propertyLoader.exceptions.PropertiesFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROPERTIES_FILE = "application.properties";
    private Properties properties;

    public PropertyLoader() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
                return;
            }
            throw new PropertiesFileNotFoundException("Não foi possível carregar o arquivo : " + PROPERTIES_FILE);
        } catch (IOException exception) {
            throw new PropertiesFileNotFoundException(PROPERTIES_FILE + " arquivo não encontrado.");
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
