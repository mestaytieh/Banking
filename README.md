# Banking
Example project showing how to develop a microservice for an online bank using Java , Java Stream, in Memory Database and Spring-boot
## Running locally
1. Java 18 required
2. for local testing run development branch

```
./mvnw clean package
java -jar target/Banking-0.0.1.jar
```
### Option2 
````
Run the springboot application from the Spring configuration (Using intellij or eclipse and the webpage will popup. 
````

## Testing
1. After Running the application locally go to the browser for localhost with port 8080
2. Page Should be clear on the fields to test for a couple of apis
3. A few apis are exposed but are not accessible from the front end(can be hit directly from the url)
4. for the data there are 2 files schema.sql which contains the schema of the inmemory database and the data.sql, 
which contains the data required for test. We can add inserts to the data.sql before running the application 
to view a new set of data

### TODO:
1. Enable Spring security for none local testing
2. Add unit tests
3. Add integration tests
4. Add User roles for different functionality and security
