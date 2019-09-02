1. Overview::
This is a demo project using Spring reactive streams support along with a MongoDB database and RESTful endpoints; it provides ticket transactions that can either validate successfully, 
fail to validate due to the randomness of the creation process, or error with an technical exception.

The business logic for determining whether a transaction is successful or not can be found here:
https://en.wikipedia.org/wiki/Bingo_(United_Kingdom)#Description_of_the_game


2. Required interface::
The interface responsible for processing the transaction data can be found here, 
com.ipsx.transaction.service.impl.ServiceImpl


3. Running this application::
This is a SpringBoot application which can be run from the Application.java class 
provided the jdk8+ is installed.
NB: There is a dependency on Mongodb where transaction data is being persisted
https://docs.mongodb.com/manual/installation/


4. Running the unit tests::
To download all the jar dependencies, build the project and run unit tests try running:
maven clean install


5. Running this application via the client::
This is not required to run the transaction-service;
however, running the client will add more visibility 
enabling one to view successfully generated sets of tickets,
it is using streaming data to accomplish this building on the reactive architecture
provided by Spring's reactive libs and offering an event-driven push model to acquiring data as opposed to polling.


6. Accessing the REST Endpoints::
Open your browser
i. View tickets in the database via http
http://localhost:8080/tickets

ii. Push transaction data reactively to web-page
http://localhost:8080/index.html


7. Querying the (MongoDB) database:
Pre-requisite: Ensure that MongoDB is installed
Open your terminal
i. connect to localhost    
mongo --host localhost:27017

ii. reveal schema
show collections

iii. show transactions
db.ticket.find( {});
