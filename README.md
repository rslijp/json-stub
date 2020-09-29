# Results

**A new(additional) API runs on:** http://localhost:9080

**To build and run(unchanged):** use `mvn compile exec:java`

Two calls are made and for disclosing aggregated data.
- One retrieves an aggregate result based on the id 

  **Example call for id:** http://localhost:9080/by-id/0001
-  The second call retrieves multiple aggregate results based on the grantee 

   **Example call for user:** http://localhost:9080/by-user/Super%20duper%20employee
- Check style is added and triggered with each compile
- Unit tests and integration tests are added. To execute them run`mvn test`. 
  No measures are taken to run the tests on a different port 
  the application must **not** run before running the test.

# Power of Attorney Service
This awesome web service provides REST API for accessing power of attorney information of a user
  - Power of attorney details such as grantee, grantor and account details (/power-of-attorneys/{id})
  - Details for card products authorized by the power of attorney (/debit-cards/{id} and /credit-cards/{id})
  - Account details (/accounts/{id})
  - Some developer might have made an error somewhere

**To build and run:** use `mvn compile exec:java`
**Application runs on:** http://localhost:8080
**REST API documentation:** http://localhost:8080/swagger/


# Exercise!
  - Build a REST API presenting aggregated information from different services
  - Only show data that a user is actually authorized for
  - Handle any server errors you might run into gracefully
  
# Requirements
  - Requirements of the code and functionality is up to the candidate
  - We suggest using Java 11, Spring-Boot & Maven, but using Kotlin or Gradle is also fine
  - Perform whatever validation seems necessary
  - Don't return inactive products or accounts
  - (Optional) Expose the API over HTTPS
 
# Tips
  - Because every candidate has different experience and background, the candidate should decide on how complex code they want to show us
  - If the assignment is unclear, do what you feel is best and focus on the code, not the exercise
  - We look at the quality and readability of code that has been delivered more than if the functionality matches our expectations
  - Impress us!

(make sure the application is stopped before running the test)