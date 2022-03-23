Installation and Deployment Guide information
This section is provided to give information on the deployment of the Robots Apocalypse API. 

Database Creation
Steps
1.	Create the Database: robotsInversiondb in Microsoft SQL Server
2.	Run the script RobotsApocalypseSpringSqlScript.sql to create tables and procedures 

Robots Apocalypse API Deployment
Steps
1.	Update the following in open and close braces below that can be found in the application.properties file in the war file with your environment db data:

spring.datasource.url=jdbc:sqlserver://{DBSERVER}:{DBPORT};Database={DBNAME};
spring.datasource.username={DBUSER}
spring.datasource.password={DBPASSWORD}

2.	Install Application server: Glassfish, wildfly, tomcat etc
3.	Deploy the war file to the autodeploy (autodeploy exists in Glassfish) folder on the server.
4.	The application will automatically be deployed.
5.	Or follow your preferred application server deployment step to deploy the application
6.	Once deployment is completed, update the API base URL domain and port (http://localhost:8080/ ) to run any of the APIs


NB: war file - apocalypse-0.0.1-SNAPSHOT.war can be found in the target folder in the source code apocalypse.



