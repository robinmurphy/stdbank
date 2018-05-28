**`Automate Everything`**

The app is a vending machine app written in Java8 (no particular reason why, besides being the language currently using) using SpringBoot. Reading http://automateeverything.io/ assumptions were
 that the requirements was to build an app, upload it to github and configure Travis to create pipelines for CI/CD and to automate as much as possible.
 The purpose of the exercise was to get the automation working from end to end so there are incomplete parts in the code

 With this in mind I created a Springboot app with the following features:
 * A Vending machine that will load data from an inMemory Db (H2)
    * Liquibase scripts were used to create tables and insert the initial data
 * A user can select products and the app will calculate the total when you initialize the basket and return the amount
 * When the basket is submitted for payment it requires payment which the app will calculate change and the denominations of change to return - The
    accepted denominations is between R50 & 10c
 * Due to time I only created the rest services and did not add a web page to handle user interaction
 * A swagger page is provided to test the rest calls
 * Not all methods/logic are complete, some unit tests were included but is not complete

 * The code was pushed to github and Travis was configured with pipelines
 * Configured Heroku for Travis to deploy to the different environments
 * 3 job stages have been defined in Travis and each needs to pass to move onto the next
    * build, run test and run code coverage
    * deploy to dev - ping the rest service
    * deploy to production



**`Tech Stack used:`**
* Java8
* Spring - (core, data, web & boot)
* Liquibase & H2 DB - inMem DB & DB versioning
* Maven - build tool
* CodeCov - Code coverage
* Travis - CI/CD
* Heroku - deloyment environment
* Junit
* Swagger - RestApi documentation

**`Links`**

Github -> public repo: https://github.com/robinmurphy/stdbank

Travis - https://travis-ci.org/robinmurphy/stdbank

Swagger - http://localhost:8080/swagger / https://stdbank.herokuapp.com/swagger-ui.html

Heroku - https://dashboard.heroku.com/pipelines/b0595ca6-2e76-4385-89ec-618c4cf8d44f
    - uname/psswd: robinmurphy1@gmail.com/1qaz@WSX

CodeCov - check Travis build logs for url

**`Downloading & running the app local`**

**`Requirements:`**
* Java 8
* mvn
* git

Either do a git clone or get the code from the link above
Run one of the commands in the project directory :
* mvn spring-boot:run
* java -jar ./target/stdbank-0.0.1-SNAPSHOT.jar

It can also be run on the Heroku server from the links above



