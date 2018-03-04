# Project Title 
  Game Product Recommendation app is to facilitate games recommendations to the customer on a website, the admin will have provision to upload csv file with list of upto 10 
  game recommmendations through http://localhost:8080/index.html. 
  
## Prerequisites
   . Windows or unit Operating system, Java 8, Maven, Docker-toolbox setup and Java IDE(Eclipse/IntelliJ Idea).
   . Optional : MySQL server.
   

### Getting Started
   
   . To setup this project and start running, clone the github url on your local workspace. Import into a IDE of your choice(Eclipse/IntelliJ Idea). 
   
   . (Optional Step- if not using docker setup:)  On the MySQL server, login as root user and run the schema.sql located in the project root path to create game schema, users and grant permissions.
      normally mvn commands on spring boot runs this automatically and generates schema but sometimes it doesnt work in some users. so keeping it explicit.
   
   After downloading/cloning project into a local IDE, run the below command.
   
   `$ mvn clean install spring-boot:run`
   
   The above command generate necessary artifacts target/ directory and run the unit tests against the rest controller. 
   Once the application is started you can use the url http://localhost:8080/index.html to upload the customer_data.csv(at root path)
   to generate some data in db for testing of exposed service to fetch the recommendations.
   
   . Login to mysql database game schema and verify the data persisted using query (select * from customer)
   
   . Use a Rest client tool like Postman to get the recommended games for the uploaded customers. Sample GET request as below:
   
   http://localhost:8080//customers/11113/games/recommendations?count=5
   
   
#### Installing with Docker
   
   The Docker setup for the app, is managed by maven and docker-compose. Make sure the docker machine is running on the local machine to ensure the daemon is up.
   
   Step 1: Run the below maven command to create the project image. 
    
   `$ mvn clean package docker:build`
   
   Step 2: Above command generates a docker image of the spring boot app by using src/main/docker/DockerFile using com.spotify.docker-maven-plugin. 
   Please verify the below 2 properies before running the commmand in user local machine
   
   <dockerHost>https://192.168.99.100:2376</dockerHost> <!--Docker host of user, you can verify by using docker machine env and modify if required-->
   <dockerCertPath>~\.docker\machine\default\</dockerCertPath> <!--Certificate path as the above url is https-->
    
   Step 3: Once the maven build is successful. The docker image will be create in the local docker registry. This can be verified by running :
   
   `$ docker images`
   
   The newly generated image will be listed here.
   
   Step 5: Now the next step would be to link the above image with the docker image of mysql server running on port 3306. 
   To achieve this, docker-compose.yml is provided with necessary orchestration of the 2 service(spring-boot and mysql)
   Next step is to navigate to the root folder and run the below command. 
   
   `$ docker-compose up`
   
   This will take some time the first time as it tries to download the image from dockerhub and start on the necessary port, create schema etc.
   
   once docker-compose is up and running. We mainly have our 2 main service( game app service and mysql service talking to each other).
   
   Step 6: Now we can use the url http://localhost:8080/index.html to upload the customer_data.csv(at root path)
   to generate some data in db for testing of exposed service to fetch the recommendations.
                 
   Step 7: Login to mysql database game schema and verify the data persisted using query (select * from customer)
                 
   Step 8: Use a Rest client tool like Postman to get the recommended games for the uploaded customers. Sample GET request as below:
                 
   http://localhost:8080//customers/11113/games/recommendations?count=5
   
##### Author

   . Anupam Shrivastava 
   . shanupam@gmail.com

###### License
   NA

####### Built With
   Java 8, Maven 3, MySQl, Docker, Spring boot, Junit, Apache Commons, common-fileupload