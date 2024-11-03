package testRunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner {


    @Test(priority = 1,description = "Login by admin")
    public void adminLogin() throws ConfigurationException, IOException {
        UserController userController=new UserController();
        String email = "admin@roadtocareer.net";
        String password = "1234";
        userController.adminLogin(email,password);
    }

    @Test(priority = 2,description = "Create customer 1")
    public void createCustomer1() throws IOException, InterruptedException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        String name = "RestassuredC1 "+faker.name().fullName();
        String password= "1234";
        String email = faker.internet().emailAddress().toLowerCase();
        String phoneNumber = "01624"+ Utils.generateRandomId(100000,999999);
        String nid = ""+Utils.generateRandomId(100000000,99999999);
        String role = "Customer";
        UserModel model=new UserModel();
        model.setName(name);
        model.setPassword(password);
        model.setEmail(email);
        model.setNid(nid);
        model.setRole(role);
        model.setPhone_number(phoneNumber);

        JsonPath jsonPath= userController.createUser(model);

        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "User created";
        Assert.assertTrue(message.contains(expectedMessage));
        Utils.saveUser(model);

    }


    @Test(priority = 3,description = "Create customer 2")
    public void createCustomer2() throws IOException, InterruptedException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        String name = "RestassuredC2 "+faker.name().fullName();
        String password= "1234";
        String email = faker.internet().emailAddress().toLowerCase();
        String phoneNumber = "01634"+ Utils.generateRandomId(100000,999999);
        String nid = ""+Utils.generateRandomId(100000000,99999999);
        String role = "Customer";
        UserModel model=new UserModel();
        model.setName(name);
        model.setPassword(password);
        model.setEmail(email);
        model.setNid(nid);
        model.setRole(role);
        model.setPhone_number(phoneNumber);

        JsonPath jsonPath= userController.createUser(model);

        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "User created";
        Assert.assertTrue(message.contains(expectedMessage));

        Utils.saveUser(model);
    }

    @Test(priority = 4,description = "Create agent")
    public void createAgent() throws IOException, InterruptedException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        String name = "RestassuredA "+faker.name().fullName();
        String password= "1234";
        String email = faker.internet().emailAddress().toLowerCase();
        String phoneNumber = "01463"+ Utils.generateRandomId(100000,999999);
        String nid = ""+Utils.generateRandomId(100000000,99999999);
        String role = "Agent";
        UserModel model=new UserModel();
        model.setName(name);
        model.setPassword(password);
        model.setEmail(email);
        model.setNid(nid);
        model.setRole(role);
        model.setPhone_number(phoneNumber);

        JsonPath jsonPath= userController.createUser(model);


        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "User created";
        Assert.assertTrue(message.contains(expectedMessage));

        Utils.saveUser(model);
    }





}
