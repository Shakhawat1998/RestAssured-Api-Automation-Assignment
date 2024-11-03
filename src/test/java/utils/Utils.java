package utils;

import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Utils {
    public  static  void setEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();
    }




    public  static int generateRandomId(int min, int max){
        double random = Math.random()*(max-min)+min;
        int randId = (int)random;
        return randId;
    }


    // save customer1 , customer2 and agent in json
    public  static void saveUser(UserModel userModel) throws IOException, ParseException {
        String filePath = "./src/test/resources/user.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filePath));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();

        userInfo.put("name",userModel.getName());
        userInfo.put("email",userModel.getEmail());
        userInfo.put("password",userModel.getPassword());
        userInfo.put("phone_number",userModel.getPhone_number());
        userInfo.put("nid",userModel.getNid());
        userInfo.put("role",userModel.getRole());

        jsonArray.add(userInfo);
        FileWriter file = new FileWriter(filePath);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();

    }




    //read json file for transaction
    public static String getPhoneNumber(int index) throws IOException, ParseException {

        String filePath = "./src/test/resources/user.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filePath));
        JSONArray jsonArray = (JSONArray) obj;
        int arraySize = jsonArray.size();
        int userIndex = arraySize - index;

        JSONObject arrayObject = (JSONObject) jsonArray.get(userIndex);
        String phone_number = arrayObject.get("phone_number").toString();
        return phone_number;

    }









}
