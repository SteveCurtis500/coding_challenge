## coding_challenge
Latitude Coding Challenge solution

### Features
I used Spring framework validation - **@Validated** annotation on the controller's GET request.
StockRequest has annotations to check for @NotBlank and @NotNull with custom messages.
Errors are handled in an **@ExceptionHandler** method, returning message with 400 Client Error HTTP status.

I had hoped to use Java8+ Stream to process the prices array but the checking and comparing in the loop is stateful so it did not lend itself to this.

### Testing

JUnit Jupiter was used for testing various price combinations.  
Web layer tests were implemented using **MockMvc**. 

In addition to the Unit tests, the solution was tested using Postman.
This is an example of the raw JSON format of the StockRequest body:-

{
    "requestId": "1",
    "startDateTime": "2021-08-11T01:00:00",
    "endDateTime": "2021-08-11T23:59:00",
    "stockprices":[
        4,
        6,
        1
    ]
}

