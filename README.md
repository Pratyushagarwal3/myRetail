#MyRetail Restful Service
MyRetail is a RESTful service that can retrieve product and price details by ID.

#Prerequisites
Java 8 or above should be installed in your system.

#Installing/Run application
Open command prompt and run the command:
java -jar <Path to Jar file>/mrRestful-0.0.1-SNAPSHOT.jar

#Exposed APIs
http://localhost:8080/products/<Productid> handels requests: 
GET-> for retrieving product details
POST-> for adding new product prices
PUT-> for updating product prices

http://localhost:8080/products/<Productid>/price handels GET request for retrieving product prices

#Versioning
1.0

#Author
Pratyush Agarwal