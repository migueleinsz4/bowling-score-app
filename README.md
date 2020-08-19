# Bowling Score App Challenge

### Reference Documentation

##### Technology:

* JDK 8
* Maven 3
* Spring Boot 2.3
* JUnit 5

##### Guidelines:

* Code against interfaces, not implementations
* Favoring object composition over class inheritance
* Favoring immutability
* Runtime Exceptions
* Use of Spring Framework Inversion of Control and Dependency Injection
* SOLID, as Spring Framework Style allows. Emphasis on Single Responsibility, 
  Interface Segregation and Dependency Inversion  

##### Features:

* Read text files.  Two columns separated by tab.  Line breaks are ignored
* General processing flow is to read result data, validate result data, calculate scores 
and print scores 
* Allows changing the Source of the data.  
If in the future you want to use other data sources, 
this is possible by creating another implementation of the SourceReaderService interface.
* Allows to change the Scoring Engine.
If in the future you want to use other rules to calculate the score, 
this is possible by creating another implementation of the ScoringEngineService interface.
* Allows to change the Print Engine. 
If in the future you want to use other printing mechanisms, 
this is possible by creating another implementation of the PrintEngineService interface.
* Easy evolution to REST API, adding layers of Controller, Repository, etc.
* Unit and Integration tests

##### Bonus:

Use of Java 8 streams and lambdas

##### Compile and Build:

* Install JDK 8
* Install Maven 3
* Download code from [GitHub](https://github.com/migueleinsz4/bowling-score-app) as zip file
* Unzip the zip file, you get the bowling-score-app-master directory
* Change to the bowling-score-app-master directory 
* Execute the command: mvn package
* The bowling-score-app-master/target directory is generated
* Copy the content of bowling-score-app-master/src/main/resources/DataFiles directory into the bowling-score-app-master/target directory

##### Run:

* Change to the bowling-score-app-master/target directory
* Execute the command: java -jar bowling-score-app-1.0.0.jar --file=test01.txt