package com.utilities;

import java.io.FileReader;
import java.util.Properties;

public class Config {
    public static String readPropertyFileData(String keyName, String fileName) {
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("user.dir") + "/configuration/" + fileName + ".properties";
            FileReader fileReader = new FileReader(filePath);
            properties.load(fileReader);

            System.out.println("Loaded properties from: " + filePath);
            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + " = " + properties.getProperty(key));
            }

            String value = properties.getProperty(keyName);
            if (value == null) {
                throw new RuntimeException("Key '" + keyName + "' not found in " + fileName + ".properties");
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Error reading config file: " + e.getMessage());
        }
    }
}
