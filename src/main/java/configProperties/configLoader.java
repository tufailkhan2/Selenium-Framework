package configProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configLoader {
    private final Properties properties = new Properties();

    public configLoader() {
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main" +
                    "\\java\\configProperties\\config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
