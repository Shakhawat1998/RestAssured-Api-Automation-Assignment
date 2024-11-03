package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController extends Setup {
    public  UserController() throws IOException {
        initConfig();
    }

    String token;
    String message;


    public  void  adminLogin(String email,String password) throws ConfigurationException {
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);

        Response res= given().contentType("application/json")
                .body(model).when()
                .post("/user/login")
                .then().assertThat()
                .statusCode(200).extract()
                .response();

        System.out.println(res.asString());
        JsonPath jsonPath= res.jsonPath();
        token=jsonPath.get("token");
        message=jsonPath.get("message");
        System.out.println(message);

        String expectedMessage = "Login successful";
        Assert.assertTrue(message.contains(expectedMessage));
        Utils.setEnvVar("token",token);

    }


    public JsonPath  createUser(UserModel model) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI= prop.getProperty("BASE_URL");
        String BearerToken = (String) prop.getProperty("token");
        Response res=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/user/create");
        System.out.println(res.asString());

        return  res.jsonPath();

    }



}
