Overview::
This is a demo project for using Spring Reactive Streams lib
It provides ticket transactions that can either validate successfully, 
fail to validate due to the randomness of the creation process, or error with an technical exception.

The business logic for determining whether a transaction is successful or not can be found here:
https://en.wikipedia.org/wiki/Bingo_(United_Kingdom)#Description_of_the_game


Running this application::
This is a SpringBoot application which can be run from the Application.java class 
provided the jdk8+ is installed
NB: There is a dependency on Mongodb where transaction data is being persisted
https://docs.mongodb.com/manual/installation/


Running the unit tests::
To download all the jar dependencies, build the project and run unit tests try:
maven clean install


Running this application via the client::
This is not required to run the transaction-service,
however running the client will add more visibility 
enabling one to view a successfully generated set of tickets
it using streaming data to accomplish this building on the reactive architecture
provided by Project Reactor and its integration with Spring/Mongodb


REST Endpoints::
Open your browser
i. View tickets in the database
http://localhost:8080/tickets

ii. Push user reactively to client
http://localhost:8080/ws.html


Querying Mongodb database:
Pre-requisite: Ensure that mongodb is installed
Open your terminal
i. connect to localhost    
mongo --host localhost:27017

ii. reveal schema
show collections

iii. show transactions
db.ticket.find( {});