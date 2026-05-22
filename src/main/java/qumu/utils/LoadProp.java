package qumu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProp {

    private static final Properties prop = new Properties();

    static {
        try (InputStream input = LoadProp.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
