# retail-api

## Required Libraries
  - [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) version 1.8 
  - [Apache Maven] (https://maven.apache.org/download.cgi) version 3.3.9

## Installation
  - Api uses [Mongodb](https://www.mongodb.com/download-center?filter=enterprise&utm_source=google&utm_campaign=Americas_US_CorpEntOnly_Brand_Beta_FM&utm_keyword=%2Bmongo%20%2B3.6%20%2Bdownload&utm_device=c&utm_network=g&utm_medium=cpc&utm_creative=241824498179&utm_matchtype=b&_bt=241824498179&_bk=%2Bmongo%20%2B3.6%20%2Bdownload&_bm=b&_bn=g&jmp=search&gclid=EAIaIQobChMIzrTU-87J2wIVkONkCh3UagHjEAAYASAAEgIs0vD_BwE#enterprise) Version 3.6

   - Install mongodb V3.6 and take a note of the installation directory
   - Run mongodb by going to the bin folder of the mongodb installation directory.  
   # In windows 
   ```sh 
      mongod --dbpath <your data directory>
   ```
   - mongo should be running with default settings on port 27017.

## Runnning Locally
```sh
c:\> git clone https://github.com/sbiswas3132/api-retail.git
c:\> cd api-retail
c:\api-retail> mvn clean install
c:\api-retail> java -jar target/productservice-0.0.1-SNAPSHOT.jar
```
    
## Test

### Get 
- Request: http://localhost:8090/api/products/13860428
- Response :
{
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price":    {
      "value": 95.49,
      "currency_code": "USD"
   }
}

### Put 
- Request : http://localhost:8090/api/products/13860428
{
   "name": "The Big Lebowski (Blu-ray)",
   "id": 13860428,
   "current_price":    {
      "value": 85.49,
      "currency_code": "USD"
   }
}
- Response: 200 Status Ok
