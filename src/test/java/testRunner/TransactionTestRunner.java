package testRunner;

import controller.TransactionController;
import io.restassured.path.json.JsonPath;
import model.TransactionModel;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner extends Setup {

    @Test(priority = 1, description = "Give 2000 tk from System account to the newly created agent")
    public void depositSystemToAgent() throws IOException, InterruptedException, ParseException {

        TransactionController transactionController= new TransactionController();
        JsonPath jsonPath= transactionController.depositFromSystemToAgent();

        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "Deposit successful";
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test(priority = 2, description = "Deposit 1500 tk to a customer from the agent account")
    public void depositeAgentToCustomer1() throws InterruptedException, IOException, ParseException {
        Thread.sleep(3000);
        String agentPhoneNumber = Utils.getPhoneNumber(1);
        String customer1PhoneNumber = Utils.getPhoneNumber(3);
        System.out.println("Agent: "+agentPhoneNumber);
        System.out.println("Customer 1: "+customer1PhoneNumber);
        int amount = 1500;
        TransactionController transactionController=new TransactionController();
        JsonPath jsonPath= transactionController.depositFromAgentToCustomer1(agentPhoneNumber, customer1PhoneNumber, amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "Deposit successful";
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test(priority = 3, description = "Withdraw 500 tk by the customer to the agent")
    public void withdrawByCustomer1() throws InterruptedException, IOException, ParseException {
        Thread.sleep(3000);
        String customer1PhoneNumber = Utils.getPhoneNumber(3);
        String agentPhoneNumber = Utils.getPhoneNumber(1);
        int amount = 500;
        TransactionController transactionController=new TransactionController();
        JsonPath jsonPath = transactionController.withdrawFromAgent(customer1PhoneNumber,agentPhoneNumber,amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "Withdraw successful";
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test(priority = 4, description = " Send money 500 tk to another customer")
    public void sendMoneyToCustomer2() throws InterruptedException, IOException, ParseException {
        Thread.sleep(3000);
        String customer1PhoneNumber = Utils.getPhoneNumber(3);
        String customer2PhoneNumber = Utils.getPhoneNumber(2);
        int amount=500;

        TransactionController transactionController=new TransactionController();
        JsonPath jsonPath= transactionController.sendMoneyToCustomer2(customer1PhoneNumber,customer2PhoneNumber,amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "Send money successful";
        Assert.assertTrue(message.contains(expectedMessage));

    }


    @Test(priority = 5, description = "Payment 100 tk to any merchant by the recipient customer")
    public  void paymentToMerchant() throws IOException, InterruptedException, ParseException {
        String customer2PhoneNumber = Utils.getPhoneNumber(2);
        String merchantNumber = "01301831905";
        int amount = 100;
        TransactionController transactionController=new TransactionController();
        JsonPath jsonPath= transactionController.PaymentToMerchant(customer2PhoneNumber,merchantNumber,amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "Payment successful";
        Assert.assertTrue(message.contains(expectedMessage));
    }

    @Test(priority = 6, description = "Check balance of the recipient customer")
    public void checkCustomerBalance() throws InterruptedException, IOException, ParseException {
        TransactionController transactionController=new TransactionController();
        JsonPath jsonPath= transactionController.checkCustomerBalance();
        String message = jsonPath.get("message");
        System.out.println(message);
        String expectedMessage = "User balance";
        Assert.assertTrue(message.contains(expectedMessage));

    }




}
