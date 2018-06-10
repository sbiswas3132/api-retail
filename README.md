# retail-api

1. Required application libraries
	* Java version 1.8
	* Apache Maven version 3.3.9
	* Mongodb Version 3.6
2. Install mongodb V3.6 and take a note of the installation directory
3. Run mongodb by going to the bin folder of the mongodb installation directory.
4. In windows 
a. mongodb --dbpath <your data director>
b. mongo should be running with default settings on port 27017.
5. Download code to a directory using git clone https://github.com/sbiswas3132/api-retail.git. 
6. Change directory to “api-retail”
7. Run “mvn clean install”
8. Run the service issuing below command. The service will be running on port 8090
a. java -jar target/productservice-0.0.1-SNAPSHOT.jar
9. Test the service using SoapUI
10. Get Request  :	http://localhost:8090/api/products/13860428

Response :
{
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price":    {
      "value": 95.49,
      "currency_code": "USD"
   }
}

11. Put Request : http://localhost:8090/api/products/13860428
{
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price":    {
      "value": 85.49,
      "currency_code": "USD"
   }
}
Response: 200 Status Ok
