# Project Title: Dmoney API Integration Automation using RestAssured
# Project Summary

This project demonstrates integration testing of the Dmoney API using RestAssured. The objective is to automate a series of financial transactions between system accounts, agents, and customers to verify the API's functionality and reliability.

### Key Features
- **Authentication**: Log in as an admin to retrieve an authorization token for secure access.
- **Account Management**: Automate the creation of new customer and agent accounts.
- **Transaction Workflow**:
  - Transfer funds from the system account to the agent.
  - Deposit and withdraw funds between agent and customer accounts.
  - Send money between customers and make payments to merchants.
- **Balance Verification**: Validate the final balance of accounts post-transactions to ensure accuracy.

### Technology used 
- Java
- Intellij idea
- TestNG
- Rest Assured
- Allure

### How to Run 
1. Clone the Project.
2. Open project in IntelIJ IDEA
3. Open ternminal
4. Give the command
   ```bash
   gradle clean test
   ```
5. To generate Allure report give the following commands serially
   ```bash
   allure generate allure-reports --clean -output
   ```
     ```bash
   allure serve allure-results
   ```
### Allure Reports

![restassured suite](https://github.com/user-attachments/assets/9f03d1a3-e2a7-4d34-8d1d-968aff379185)

![restassured behaviour](https://github.com/user-attachments/assets/872ef97c-7ad6-4845-9e48-2a24f86688f9)



