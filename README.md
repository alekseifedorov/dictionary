1. This is a REST service based on Jersey framework and Spring Boot that exposes the following REST endpoints.
    The Swagger 2.0 specification of the service can be found on  http://localhost:8080/swagger.json

    a. Add or update a word in the dictionary. To add or remove a synonym, fill in the synonyms array field respectively.
      curl --location --request POST "http://localhost:8080/dictionary/entry" --header "Content-Type: application/json" --data-raw "{ \"key\": \"dictionary\", \"value\": \"Woerterbuch\" }"
      curl --location --request POST "http://localhost:8080/dictionary/entry" --header "Content-Type: application/json" --data-raw "{ \"key\": \"lexicon\", \"value\": \"Lexicon\"] }"
      
      curl --location --request POST "http://localhost:8080/dictionary/entry" --header "Content-Type: application/json" --data-raw "{ \"key\": \"dictionary\", \"value\": \"Woerterbuch\", \"synonyms\": [\"lexicon\"] }"

    b. Remove a word from the dictionary
      curl --location --request DELETE 'http://localhost:8080/dictionary/entry/dictionary'

    c. Check whether a given word exists in the dictionary
      curl --location --head 'http://localhost:8080/dictionary/entry/dictionary'

    d. Retrieve an entry including all synonyms for a given word
      curl --location --request GET "http://localhost:8080/dictionary/entry/encyclopedia"

      The response might be the following :
  {
      "key": "lexicon",
      "value": "Lexicon",
      "synonyms": [
          "dictionary"
      ]
  }
             

2. How to build

    Type 'gradlew clean build' to build dictionary-1.0.jar.

    The server port is specified in application.properties (server.port=8080)

3. How to run

  Type either of the following commands:
 -  gradlew build && java -jar build/libs/dictionary-1.0.jar
 -  gradlew bootRun
