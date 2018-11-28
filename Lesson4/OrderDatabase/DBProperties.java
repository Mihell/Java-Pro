import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    private final String url;
    private final String user;
    private final String password;

    public DBProperties(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        try{
            properties.load(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = properties.getProperty("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
