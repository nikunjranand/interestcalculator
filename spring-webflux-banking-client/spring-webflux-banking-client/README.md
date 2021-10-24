# interestcalculator subscirber Application : spring-webflux-banking-client
This application act as Subscriber which subscribe event Account creation and balance events published by publisher.
It receives account information and stored account details in to Database. Below end points are created to support demo.

http://localhost:8081/account/create

http://localhost:8081/account/getAllAccounts

http://localhost:8081/account/balance

http://localhost:8081/account/calculateDailyInterest

http://localhost:8081/account/generateMonthlyInterestReport


1.Tools & Technology 

    •	Spring Boot WebFLux
    
    •	JDK 8
    
    •	Maven
    
    •	Postman
    
    •	IntelliJ Idea IDE

2. Repository

    https://github.com/nikunjranand/interestcalculator.git

3. Download Dependency

    mvn clean install

4. Pending Enhancement
   
   •	To calculate daily interest, we can write a cron job with cron expression which will trigger once at end of the day and 
        update the interest detail of that particular day.We can configure below service method as executable class for cron job.
   
        Class Name : AccountService
        Method Name : updateDailyInterest

   •	To generate monthly report, we can also write a cron job with cron expression which will trigger once at end of the month 
        which will total all the interest occurred for that particular month for all account and publish that data using Mono Flux. 
        We can configure below service method as executable class for cron job.
   
        Class Name : AccountService
        Method Name : getMonthlyInterestReport

    •	Need to create table to store monthly report data for history purpose.
   
   •	Need to write Junit Test cases.

    






