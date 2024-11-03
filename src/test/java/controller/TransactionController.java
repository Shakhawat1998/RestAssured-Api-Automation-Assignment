package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransactionModel;
import org.json.simple.parser.ParseException;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TransactionController extends Setup {
    public TransactionController() throws IOException {
        initConfig();
    }

    public JsonPath depositFromSystemToAgent() throws InterruptedException, IOException, ParseException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        String AgentNumber =  Utils.getPhoneNumber(1);
        int amount = 2000;
        TransactionModel model = new TransactionModel("SYSTEM",AgentNumber,amount);
        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/transaction/deposit");
        System.out.println(response.asString());
        return response.jsonPath();

    }

    public JsonPath depositFromAgentToCustomer1(String agentPhoneNumber, String customerPhoneNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        TransactionModel model = new TransactionModel(agentPhoneNumber,customerPhoneNumber, amount);
        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/transaction/deposit");
        System.out.println(response.asString());
        return response.jsonPath();

    }

    public JsonPath withdrawFromAgent(String customerPhoneNumber, String agentPhoneNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        TransactionModel model = new TransactionModel(customerPhoneNumber,agentPhoneNumber, amount);
        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/transaction/withdraw");
        System.out.println(response.asString());
        return response.jsonPath();
    }

    public JsonPath sendMoneyToCustomer2(String fromCustomer1Number, String toCustomer2Number, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        TransactionModel model = new TransactionModel(fromCustomer1Number,toCustomer2Number, amount);
        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/transaction/sendmoney");
        System.out.println(response.asString());
        return response.jsonPath();
    }

    public JsonPath PaymentToMerchant(String fromNumber, String toNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        TransactionModel model = new TransactionModel(fromNumber,toNumber, amount);

        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .body(model).when().post("/transaction/payment");
        System.out.println(response.asString());
        return response.jsonPath();

    }


    public JsonPath checkCustomerBalance() throws InterruptedException, IOException, ParseException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("BASE_URL");
        String BearerToken =  prop.getProperty("token");
        Response response=given().contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretKey"))
                .when().get("/transaction/balance/"+ Utils.getPhoneNumber(2));
        System.out.println(response.asString());
        return response.jsonPath();

    }




}
