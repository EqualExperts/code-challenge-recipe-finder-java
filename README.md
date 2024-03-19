# Recipe Finder service

This service allows the consumer to search its recipe database for recipes that match the search criteria provided. 

## Requirements

- Java 11
- Python 3.11
- pip

## Running the tests

./mvnw test

## Running the service

* Open Intellij IDEA,
* File | New Project from Existing Sources
  - Select the recipe-finder directory
  - Select _Import project from existing sources_
  - Select Maven
* Create a run configuration to run src/main/java/RecipeFinderApplication

This starts up a Java service with an in-memory database.

The Java service is dependent on the [recipes API](https://equalexperts.github.io/code-challenge-recipes-api).

To import data into the database from the recipes API, send a GET request to http://localhost:8080/admin/import. 

You can search for recipes by navigating to http://localhost:8080/recipes

