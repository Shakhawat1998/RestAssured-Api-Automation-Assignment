package setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {
    public static Properties prop;
    FileInputStream fs;


    public void initConfig() throws IOException {
        prop= new Properties();
        fs= new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }



}
