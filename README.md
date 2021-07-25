#Target Retail Product API
API provides product details on basis of the Product ID. 

The design of the API is managed in SwaggerHub 

Open API tool used to design the API.

## Json Postman collection 
  Provided json collection under project resource folder to test the application. Server variable has to be confirgured in the enviorment to test the application in deployed host respectively.
  

## Local Dev

- Tools:

    - Maven
    - Spring Tool Suite / Eclipse
    - Spring Tools add-on (if not included above) 
	- Cassandra
	- JAVA 1.8 or Higher
    
- Setup required:
  Cassandra database.
  Maven.
  Spring Tools.
  Java 1.8.0 or Higher .

- Build application 
  Use maven (springboot )commands to build the application  

## Controllers

### 
`/targetretail/products/{id}`
Allows for lookup of Product data.Returns a product data in JSON format on basis of the product Id passed.
Choose HTTP GET method to get the product details by passing product id in the request. 
Choose HTTP PUT to update the product price by passing the product id and product object as the body.

Configurations
 - Update the port number and context path in application.yml as needed.
 - Cassandra.yml has the configs required for cassandra operations . Update the configs as required eg: Keyname space , Cluster name.
 - Application creates the Cassandra keyname space and the table for the API operations at the application startup.
 
 
 



