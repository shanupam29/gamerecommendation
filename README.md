Game Product Recommendation:

Overview:
Set of services exposed to provide games recommendation for a given customer id. With an exposed
service to upload csv file with list of games for a given customer id(s). Sample csv is at located
at the root path of the project, with name customer_data.csv. Can be used to test the upload functionality.


Technical Overview:
Restful Services using Spring boot and persistance layer provided by Spring Data JPA along
with several 3rd party components for mapper and csv parsing. MySQl database is used to persist the customer data.
With set of Unit test cases to provide testing of the services for positive and negative scenarios.

Installation instruction:
After downloading/cloning project into a local IDE,
run the below command.
 $ mvn clean install spring-boot:run
to generate necessary artifacts target/ directory. Else just run the main program as spring boot main application GameProductRecommendationApplication
Once the application is started you can use the url localhost:8080/index.html to upload the customer_data.csv(at root path)
to generate some data in db for testing of exposed service to fetch the recommendations.