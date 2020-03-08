This project was made with [maven](https://maven.apache.org/) archetype and use [junit](https://junit.org/junit5/) for tests

##### What you need
* [Java Version 8](https://www.java.com/pt_BR/)
* [Maven](https://maven.apache.org/)
* [JUnit](https://junit.org/junit5/)

##### How to **test**
* If you want to run all test in a Class:
    * `mvn -DTest=NameOfClass`
  
* To run entire tests in project:
    * `mvn test`
 
* To run main class:
    * `mvn exec:java -Dexec.mainClass=com.intercom.msf.App`
   
