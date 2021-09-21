# DraftTicketApplication

### Running the application

To run the application you have several options:

1) Run through Intellij , by running the DraftTicketApplication configuration
2) Run through command line via command : `./gradlew bootRun`
3) Run through the Gradle bootRun task
4) Run gradle built bootJar:
    1) Create the jar by running ./gradlew bootJar
    2) navigate to build/libs and the DraftTicketApplication-0.0.1-SNAPSHOT.jar should be visible
    3) Execute the jar file via the following command: `java -jar .\DraftTicketApplication-0.0.1-SNAPSHOT.jar
       `

### Application structure

The DraftTicketApplication structure is quite straightforward.

The main package name is
`com.product.ticketapp`.

The `com.product.ticketapp.controllers` package holds the DraftTicketController, which is used as the main API for POST
requests, and the ValidationExceptionsControllerAdvice, which is an ControllerAdvice class on how to deal with
validation exceptions(since normally on validation responses there are no error messages, or they are messy)

The `com.product.ticketapp.entities` package holds all the required entities/data structures for the whole application:

1) RequestEntityDraftTicket - the request object needed to be submitted on each request
2) ResponseEntityTickets - the response of the POST method containing all the prices/costs
3) Luggage - data structure corresponding to a passengers' luggage with a dummy field
4) Passenger - data structure representing the passenger
5) Ticket - data structure representing individual passengers tickets/prices
6) ValidationErrorResponse - data structure used when there are validation errors

The `com.product.ticketapp.interfaces` package contains the TicketInterface, which could be used for different services.

The `com.product.ticketapp.services` package contains the DraftTicketService service, which is responsible for all of
the price calculations and the construction of the response entity.

### Application tests

There are 3 main testing packages contained in the application:

1) `com.product.ticketapp.FullTest` - which tests the application end to end and confirms that everything is working.
   This includes validation tests as well.
2) `com.product.ticketapp.SmokeTests` - which checks correct bean instantiations
3) `com.product.ticketapp.UnitTests` - Contains the unit tests for the DraftTicketService