package utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertyReader {

    @SneakyThrows
    public Properties readPropertiesFromFile(String filePath) {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream(filePath));
        return props;
    }
}
